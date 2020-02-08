package cl.hiperactivo.javapi.unificado.utils.utils;

import cl.hiperactivo.javapi.unificado.utils.util.JSONHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

public class JSONHelperTest {

  @Test
  public void serializationTest() {
    TestModel model = new TestModel();
    model.setVar1("test1");
    model.setVar2(1L);
    String json = JSONHelper.toJSON(model);
    Assert.assertEquals("{\"var1\":\"test1\",\"var2\":1}", json);
  }

  @Test
  public void deserializationWithClassTest() {
    String json = "{\"var1\":\"test2\",\"var2\":2,\"var3\":\"unexpected\"}";
    TestModel model = JSONHelper.fromJSON(json, TestModel.class);
    Assert.assertEquals("test2", model.getVar1());
    Assert.assertEquals(2L, model.getVar2().longValue());
  }

  @Test
  public void deserializationWithTypeReferenceTest() {
    List<TestModel> list = new ArrayList<>();
    list.add(new TestModel("var1", 1L));
    list.add(new TestModel("var2", 2L));
    list.add(new TestModel("var3", 3L));

    String json = JSONHelper.toJSON(list);

    List<TestModel> list2 = JSONHelper.fromJSON(json, new TypeReference<List<TestModel>>() {
    });
    Assert.assertEquals(list, list2);
  }

  static public class TestModel {

    private String var1;
    private Long var2;

    public TestModel() {

    }

    public TestModel(String var1, Long var2) {
      this.var1 = var1;
      this.var2 = var2;
    }

    public String getVar1() {
      return var1;
    }

    public void setVar1(String var1) {
      this.var1 = var1;
    }

    public Long getVar2() {
      return var2;
    }

    public void setVar2(Long var2) {
      this.var2 = var2;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      TestModel testModel = (TestModel) o;
      return Objects.equals(var1, testModel.var1) &&
          Objects.equals(var2, testModel.var2);
    }

    @Override
    public int hashCode() {
      return Objects.hash(var1, var2);
    }
  }
}
