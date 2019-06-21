package com.geo.source.testmain.publictest;

import java.util.HashSet;
import java.util.Set;

/**
 * 雪花算法
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 *
 * @author YanZhen
 * @since 2019-06-20 17:52
 */
public class SnowFlake {
  private final long startTimestamp = 1480166465631L;
  private final long sequenceBits = 12L;
  private final long workerIdBits = 5L;
  private final long dataCenterIdBits = 5L;
  private final long sequenceMask = ~(-1L << sequenceBits);
  private final long maxWorkerId = ~(-1L << workerIdBits);
  private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

  private final long workerIdShift = sequenceBits;
  private final long dataCenterIdShift = sequenceBits + workerIdBits;
  private final long timestampLeftShift = dataCenterIdShift + dataCenterIdBits;

  private long workerId;
  private long dataCenterId;

  private long sequence = 0L;// 序列号
  private long lastTimestamp = -1L;

  /**
   * @param workerId     工作id
   * @param dataCenterId 数据中心id
   */
  public SnowFlake(long workerId, long dataCenterId) {
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException("worker Id can't be greater than " + maxWorkerId + " or less than 0");
    }

    if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
      throw new IllegalArgumentException("data center Id can't be greater than " + maxDataCenterId + " or less than 0");
    }

    this.workerId = workerId;
    this.dataCenterId = dataCenterId;
  }

  synchronized long nextId() {
    long newStamp = getNewStamp();
    if (newStamp < lastTimestamp) {
      throw new RuntimeException("Clock moved backwards. Refusing to generate Id！");
    }

    if (newStamp == lastTimestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0L) {
        newStamp = getNextMill();
      }
    } else {
      sequence = 0L;
    }

    lastTimestamp = newStamp;

    return (newStamp - startTimestamp) << (timestampLeftShift)// 时间戳
            | dataCenterId << (dataCenterIdShift)// 数据中心id
            | workerId << (workerIdShift)// 工作id
            | sequence;// 序列号
  }

  /**
   * 获取下一毫秒
   *
   * @return 时间戳
   */
  private long getNextMill() {
    long newStamp = getNewStamp();
    while (newStamp < lastTimestamp) {
      newStamp = getNewStamp();
      System.out.print(".");
      System.out.println();
    }
    return newStamp;
  }

  /**
   * 系统时间戳（毫秒）
   *
   * @return 时间戳
   */
  private long getNewStamp() {
    return System.currentTimeMillis();
  }
}

/**
 * 测试SnowFlake算法
 */
class Main {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  private static void m2() {
    final Set<Long> ids = new HashSet<>();
    final CustomSnowFlake customSnowFlake = new CustomSnowFlake(1, 1);
    int num = 10000;
    for (int i = 0; i < num; i++) {
      final long id = customSnowFlake.nextId();
      ids.add(id);
      System.out.println(System.currentTimeMillis()+"=="+id);
    }

    System.out.println(num == ids.size());
  }

  private static void m1() {
    final Set<Long> ids = new HashSet<>();
    final SnowFlake snowFlake = new SnowFlake(10, 9);
    int num = 10000;
    for (int i = 0; i < num; i++) {
      ids.add(snowFlake.nextId());
    }

    System.out.println(num == ids.size());
  }
}

class CustomSnowFlake {
  /**
   * 开始时间
   */
  private static final long startTimestamp = 1561077901000L;

  /** 最后一次时间戳 */
  private static long lastTimestamp = -1L;

  // 序列号
  private static long sequence = 0L;
  private static final long sequenceBits = 12;
  private static final long maxSequence = ~(-1L << sequenceBits);
  /** 工作线程 */
  private static final long workerIdBits = 5;
  private static final long workerIdShift = sequenceBits;
  private static final long maxWorkerId = ~(-1L << workerIdBits);
  /** 数据中心 */
  private static final long dataCenterIdBits = 5;
  private static final long dataCenterIdShift = workerIdShift + workerIdBits;
  private static final long maxDataCenterId = ~(-1L << dataCenterIdBits);
  /** 时间戳 */
  private static final long timestampShift = dataCenterIdShift + dataCenterIdBits;

  private long dataCenterId;
  private long workerId;


  public CustomSnowFlake(long dataCenterId, long workerId) {
    if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
      throw new IllegalArgumentException("数据中心id需要在0-31之间！");
    }

    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException("工作id需要在0-31之间！");
    }
    this.dataCenterId = dataCenterId;
    this.workerId = workerId;
  }

  public synchronized long nextId() {
    // 查看时间是否回流
    long timestamp = getTimestamp();
    if (timestamp < lastTimestamp) {
      throw new RuntimeException("时间回调，拒绝生成id");
    }

    // 查看时间是否在同一毫秒内
    if (timestamp == lastTimestamp) {
      sequence = (sequence + 1) & maxSequence;
      if (sequence == 0) {
        timestamp = getNextMill();
        sequence = 0;
      }
    } else {
      sequence = 0;
    }

    lastTimestamp = timestamp;

    return (timestamp - startTimestamp) << timestampShift
            | dataCenterId << dataCenterIdShift
            | workerId << workerIdShift
            | sequence;
  }

  /**
   * 获取下一毫秒
   *
   * @return 时间戳
   */
  private long getNextMill() {
    long timestamp = getTimestamp();
    while (timestamp <= lastTimestamp) {
      timestamp = getTimestamp();
    }
    return timestamp;
  }

  /**
   * 时间戳
   *
   * @return 当前时间的时间戳
   */
  private long getTimestamp() {
    return System.currentTimeMillis();
  }
}