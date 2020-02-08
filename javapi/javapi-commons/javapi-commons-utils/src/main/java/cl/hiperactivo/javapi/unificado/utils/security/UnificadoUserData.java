package cl.hiperactivo.javapi.unificado.utils.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnificadoUserData implements Serializable {

  private static final long serialVersionUID = -8697932520081987301L;
  private String clientId;
  private String entityId;
  private String name;
  private String username;
  private Long id;
  private UnificadoAccessLevel accessLevel;
  private List<String> groups = new ArrayList<>();
  private Long timestamp;
  private Map<String,String> metadata=new HashMap<>();

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UnificadoAccessLevel getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(UnificadoAccessLevel accessLevel) {
    this.accessLevel = accessLevel;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }
}
