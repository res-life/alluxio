// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/common.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.MetricType}
 */
public enum MetricType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * GAUGE is the simplest type of metric. If you're not sure which to use, gauge is a safe choice. It is represents a
   * general K-V pair.
   * </pre>
   *
   * <code>GAUGE = 0;</code>
   */
  GAUGE(0),
  /**
   * <pre>
   * COUNTER represents values which can be incremented or decremented over time by certain operations. It does not rely
   * on timing to determine the value.
   * </pre>
   *
   * <code>COUNTER = 1;</code>
   */
  COUNTER(1),
  /**
   * <pre>
   * METER represents a metric value at a _rate_. The value of the metric varies with the time over which events are
   * recorded
   * </pre>
   *
   * <code>METER = 2;</code>
   */
  METER(2),
  /**
   * <pre>
   * TIMER represents a histogram of the rate of the specified events.
   * </pre>
   *
   * <code>TIMER = 3;</code>
   */
  TIMER(3),
  ;

  /**
   * <pre>
   * GAUGE is the simplest type of metric. If you're not sure which to use, gauge is a safe choice. It is represents a
   * general K-V pair.
   * </pre>
   *
   * <code>GAUGE = 0;</code>
   */
  public static final int GAUGE_VALUE = 0;
  /**
   * <pre>
   * COUNTER represents values which can be incremented or decremented over time by certain operations. It does not rely
   * on timing to determine the value.
   * </pre>
   *
   * <code>COUNTER = 1;</code>
   */
  public static final int COUNTER_VALUE = 1;
  /**
   * <pre>
   * METER represents a metric value at a _rate_. The value of the metric varies with the time over which events are
   * recorded
   * </pre>
   *
   * <code>METER = 2;</code>
   */
  public static final int METER_VALUE = 2;
  /**
   * <pre>
   * TIMER represents a histogram of the rate of the specified events.
   * </pre>
   *
   * <code>TIMER = 3;</code>
   */
  public static final int TIMER_VALUE = 3;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static MetricType valueOf(int value) {
    return forNumber(value);
  }

  public static MetricType forNumber(int value) {
    switch (value) {
      case 0: return GAUGE;
      case 1: return COUNTER;
      case 2: return METER;
      case 3: return TIMER;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<MetricType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      MetricType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<MetricType>() {
          public MetricType findValueByNumber(int number) {
            return MetricType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return alluxio.grpc.CommonProto.getDescriptor().getEnumTypes().get(1);
  }

  private static final MetricType[] VALUES = values();

  public static MetricType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private MetricType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.MetricType)
}
