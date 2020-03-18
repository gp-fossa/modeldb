// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.codec.binary.Hex;

public class ConfigDiff implements ProtoType {
  public List<HyperparameterSetConfigDiff> HyperparameterSet;
  public List<HyperparameterConfigDiff> Hyperparameters;

  public ConfigDiff() {
    this.HyperparameterSet = null;
    this.Hyperparameters = null;
  }

  public Boolean isEmpty() {
    if (this.HyperparameterSet != null
        && !this.HyperparameterSet.equals(null)
        && !this.HyperparameterSet.isEmpty()) {
      return false;
    }
    if (this.Hyperparameters != null
        && !this.Hyperparameters.equals(null)
        && !this.Hyperparameters.isEmpty()) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": \"ConfigDiff\", \"fields\": {");
    boolean first = true;
    if (this.HyperparameterSet != null
        && !this.HyperparameterSet.equals(null)
        && !this.HyperparameterSet.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"HyperparameterSet\": " + HyperparameterSet);
      first = false;
    }
    if (this.Hyperparameters != null
        && !this.Hyperparameters.equals(null)
        && !this.Hyperparameters.isEmpty()) {
      if (!first) sb.append(", ");
      sb.append("\"Hyperparameters\": " + Hyperparameters);
      first = false;
    }
    sb.append("}}");
    return sb.toString();
  }

  @Override
  public String getSHA() throws NoSuchAlgorithmException {
    StringBuilder sb = new StringBuilder();
    sb.append("ConfigDiff");
    if (this.HyperparameterSet != null
        && !this.HyperparameterSet.equals(null)
        && !this.HyperparameterSet.isEmpty()) {
      sb.append("::HyperparameterSet::").append(HyperparameterSet);
    }
    if (this.Hyperparameters != null
        && !this.Hyperparameters.equals(null)
        && !this.Hyperparameters.isEmpty()) {
      sb.append("::Hyperparameters::").append(Hyperparameters);
    }

    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
    return new String(new Hex().encode(hash));
  }

  // TODO: not consider order on lists
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof ConfigDiff)) return false;
    ConfigDiff other = (ConfigDiff) o;

    {
      Function3<List<HyperparameterSetConfigDiff>, List<HyperparameterSetConfigDiff>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<HyperparameterSetConfigDiff, HyperparameterSetConfigDiff, Boolean>
                            f2 = (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.HyperparameterSet != null || other.HyperparameterSet != null) {
        if (this.HyperparameterSet == null && other.HyperparameterSet != null) return false;
        if (this.HyperparameterSet != null && other.HyperparameterSet == null) return false;
        if (!f.apply(this.HyperparameterSet, other.HyperparameterSet)) return false;
      }
    }
    {
      Function3<List<HyperparameterConfigDiff>, List<HyperparameterConfigDiff>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<HyperparameterConfigDiff, HyperparameterConfigDiff, Boolean> f2 =
                            (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x.equals(false))
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.Hyperparameters != null || other.Hyperparameters != null) {
        if (this.Hyperparameters == null && other.Hyperparameters != null) return false;
        if (this.Hyperparameters != null && other.Hyperparameters == null) return false;
        if (!f.apply(this.Hyperparameters, other.Hyperparameters)) return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.HyperparameterSet, this.Hyperparameters);
  }

  public ConfigDiff setHyperparameterSet(List<HyperparameterSetConfigDiff> value) {
    this.HyperparameterSet = Utils.removeEmpty(value);
    return this;
  }

  public ConfigDiff setHyperparameters(List<HyperparameterConfigDiff> value) {
    this.Hyperparameters = Utils.removeEmpty(value);
    return this;
  }

  public static ConfigDiff fromProto(ai.verta.modeldb.versioning.ConfigDiff blob) {
    if (blob == null) {
      return null;
    }

    ConfigDiff obj = new ConfigDiff();
    {
      Function<ai.verta.modeldb.versioning.ConfigDiff, List<HyperparameterSetConfigDiff>> f =
          x ->
              blob.getHyperparameterSetList().stream()
                  .map(HyperparameterSetConfigDiff::fromProto)
                  .collect(Collectors.toList());
      obj.HyperparameterSet = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.ConfigDiff, List<HyperparameterConfigDiff>> f =
          x ->
              blob.getHyperparametersList().stream()
                  .map(HyperparameterConfigDiff::fromProto)
                  .collect(Collectors.toList());
      obj.Hyperparameters = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.ConfigDiff.Builder toProto() {
    ai.verta.modeldb.versioning.ConfigDiff.Builder builder =
        ai.verta.modeldb.versioning.ConfigDiff.newBuilder();
    {
      if (this.HyperparameterSet != null
          && !this.HyperparameterSet.equals(null)
          && !this.HyperparameterSet.isEmpty()) {
        Function<ai.verta.modeldb.versioning.ConfigDiff.Builder, Void> f =
            x -> {
              builder.addAllHyperparameterSet(
                  this.HyperparameterSet.stream()
                      .map(y -> y.toProto().build())
                      .collect(Collectors.toList()));
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Hyperparameters != null
          && !this.Hyperparameters.equals(null)
          && !this.Hyperparameters.isEmpty()) {
        Function<ai.verta.modeldb.versioning.ConfigDiff.Builder, Void> f =
            x -> {
              builder.addAllHyperparameters(
                  this.Hyperparameters.stream()
                      .map(y -> y.toProto().build())
                      .collect(Collectors.toList()));
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitConfigDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepListOfHyperparameterSetConfigDiff(this.HyperparameterSet);
    visitor.preVisitDeepListOfHyperparameterConfigDiff(this.Hyperparameters);
  }

  public ConfigDiff postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitConfigDiff(this);
  }

  public ConfigDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setHyperparameterSet(
        visitor.postVisitDeepListOfHyperparameterSetConfigDiff(this.HyperparameterSet));
    this.setHyperparameters(
        visitor.postVisitDeepListOfHyperparameterConfigDiff(this.Hyperparameters));
    return this.postVisitShallow(visitor);
  }
}
