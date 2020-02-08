package cl.hiperactivo.javapi.unificado.utils.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//TODO Cambiar synchronized por read/write lock.
public class ClientEntityAuthorizationContainter {
    private Map<String, ClientEntityAuthorization> authorizations;
    private Long maxIdleTime = 3600000L;
    private Integer maxSize = 1000;

    public ClientEntityAuthorizationContainter() {
        authorizations = new HashMap<>();
    }

    public Long getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(Long maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized String find(String entity, Optional<Supplier<String>> supplier) {
        ClientEntityAuthorization authorization = this.authorizations.get(entity);
        if (authorization == null) {
            if (supplier.isPresent()) {
                String authorizationKey = supplier.get().get();
                this.register(entity, authorizationKey);
                authorization = this.authorizations.get(entity);
            } else return null;
        }
        authorization.setLastAccess(new Date());
        return authorization.getPrivateKey();
    }


    public synchronized void register(String entityId, String privateKey) {
        this.authorizations.putIfAbsent(entityId, new ClientEntityAuthorization(entityId, privateKey));
    }

    public synchronized void cleanup() {
        AtomicInteger gap = new AtomicInteger(this.authorizations.size() - this.maxSize);
        this.authorizations = this.authorizations.values().parallelStream().filter(e -> !e.isTooOld(this.maxIdleTime, gap))
                .sorted().sequential().filter(e -> gap.decrementAndGet() < 0)
                .collect(Collectors.toMap(e -> e.getEntity(), e -> e));
    }
    public synchronized void empty() {
       this.authorizations.clear();
       this.authorizations=new HashMap<>();
    }


    private final class ClientEntityAuthorization implements Comparable<ClientEntityAuthorization> {
        private String entity;
        private String privateKey;
        private Date lastAccess;

        public ClientEntityAuthorization(String entity, String privateKey) {
            this.entity = entity;
            this.privateKey = privateKey;
            this.lastAccess = new Date();
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public Date getLastAccess() {
            return lastAccess;
        }

        public void setLastAccess(Date lastAccess) {
            this.lastAccess = lastAccess;
        }

        @Override
        public int compareTo(ClientEntityAuthorization o) {
            return this.getLastAccess().compareTo(o.getLastAccess());
        }

        public boolean isTooOld(Long maxAge, AtomicInteger counter) {
            boolean isTooOld = System.currentTimeMillis() - this.getLastAccess().getTime() > maxAge;
            if (isTooOld) {
                counter.decrementAndGet();
            }
            return isTooOld;
        }
    }
}