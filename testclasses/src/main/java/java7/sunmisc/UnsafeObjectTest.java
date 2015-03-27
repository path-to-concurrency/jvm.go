package java7.sunmisc;

import java.lang.reflect.Field;
import libs.junit.UnitTestRunner;
import org.junit.Test;
import sun.misc.Unsafe;
import static org.junit.Assert.*;

public class UnsafeObjectTest {
    
    private static final Unsafe unsafe;
    private static final long floatArrBaseOffset;
    private static final long floatArrIndexScale;
    private static final long doubleArrBaseOffset;
    private static final long doubleArrIndexScale;
    private static final long objectArrBaseOffset;
    private static final long objectArrIndexScale;
    private static final long fOffset;
    private static final long dOffset;
    
    private boolean z;
    private byte b;
    private char c;
    private short s;
    private int i;
    private long j;
    private float f;
    private double d;
    
    static {
        unsafe = UnsafeGetter.getUnsafe();
        floatArrBaseOffset = unsafe.arrayBaseOffset(float[].class);
        floatArrIndexScale = unsafe.arrayIndexScale(float[].class);
        doubleArrBaseOffset = unsafe.arrayBaseOffset(double[].class);
        doubleArrIndexScale = unsafe.arrayIndexScale(double[].class);
        objectArrBaseOffset = unsafe.arrayBaseOffset(Object[].class);
        objectArrIndexScale = unsafe.arrayIndexScale(Object[].class);
        try {
            fOffset = unsafe.objectFieldOffset(UnsafeObjectTest.class.getDeclaredField("f"));
            dOffset = unsafe.objectFieldOffset(UnsafeObjectTest.class.getDeclaredField("d"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void booleanArray() {
        boolean[] arr = {false, true, false};
        
        long booleanArrBaseOffset = unsafe.arrayBaseOffset(boolean[].class);
        long booleanArrIndexScale = unsafe.arrayIndexScale(boolean[].class);
        long index1 = booleanArrBaseOffset + booleanArrIndexScale;
        
        assertEquals(true, unsafe.getBoolean(arr, index1));
        unsafe.putBoolean(arr, index1, false);
        assertEquals(false, unsafe.getBoolean(arr, index1));
    }
    
    @Test
    public void booleanField() {
        UnsafeObjectTest obj = new UnsafeObjectTest();
        long zOffset = objectFieldOffset("z");
        
        assertEquals(false, unsafe.getBoolean(obj, zOffset));
        unsafe.putBoolean(obj, zOffset, true);
        assertEquals(true, unsafe.getBoolean(obj, zOffset));
    }
    
    @Test
    public void byteArray() {
        byte[] arr = {1, 3, 8};
        
        long byteArrBaseOffset = unsafe.arrayBaseOffset(byte[].class);
        long byteArrIndexScale = unsafe.arrayIndexScale(byte[].class);
        long index1 = byteArrBaseOffset + byteArrIndexScale;
        
        assertEquals((byte)3, unsafe.getByte(arr, index1));
        unsafe.putByte(arr, index1, (byte)120);
        assertEquals((byte)120, unsafe.getByte(arr, index1));
    }
    
    @Test
    public void byteField() {
        UnsafeObjectTest obj = new UnsafeObjectTest();
        long bOffset = objectFieldOffset("b");
        
        assertEquals((byte)0, unsafe.getByte(obj, bOffset));
        unsafe.putByte(obj, bOffset, (byte)17);
        assertEquals((byte)17, unsafe.getByte(obj, bOffset));
    }
    
    @Test
    public void charArray() {
        char[] arr = {'x', 'y', 'z'};
        
        long charArrBaseOffset = unsafe.arrayBaseOffset(char[].class);
        long charArrIndexScale = unsafe.arrayIndexScale(char[].class);
        long index1 = charArrBaseOffset + charArrIndexScale;
        
        assertEquals('y', unsafe.getChar(arr, index1));
        unsafe.putChar(arr, index1, 'a');
        assertEquals('a', unsafe.getChar(arr, index1));
    }
    
    @Test
    public void charField() {
        UnsafeObjectTest obj = new UnsafeObjectTest();
        long cOffset = objectFieldOffset("c");
        
        assertEquals('\0', unsafe.getChar(obj, cOffset));
        unsafe.putChar(obj, cOffset, 'x');
        assertEquals('x', unsafe.getChar(obj, cOffset));
    }
    
    @Test
    public void shortArray() {
        short[] arr = {3, 4, 5};
        
        long shortArrBaseOffset = unsafe.arrayBaseOffset(short[].class);
        long shortArrIndexScale = unsafe.arrayIndexScale(short[].class);
        long index1 = shortArrBaseOffset + shortArrIndexScale;
        
        assertEquals(4, unsafe.getShort(arr, index1));
        unsafe.putShort(arr, index1, (short)12345);
        assertEquals((short)12345, unsafe.getShort(arr, index1));
    }
    
    @Test
    public void shortField() {
        UnsafeObjectTest obj = new UnsafeObjectTest();
        long sOffset = objectFieldOffset("s");
        
        assertEquals((short)0, unsafe.getShort(obj, sOffset));
        unsafe.putShort(obj, sOffset, (short)12345);
        assertEquals((short)12345, unsafe.getShort(obj, sOffset));
    }
    
    @Test
    public void intArray() {
        int[] arr = {3, 4, 5};
        
        long intArrBaseOffset = unsafe.arrayBaseOffset(int[].class);
        long intArrIndexScale = unsafe.arrayIndexScale(int[].class);
        long index1 = intArrBaseOffset + intArrIndexScale;
        
        assertEquals(4, unsafe.getInt(arr, index1));
        unsafe.putInt(arr, index1, 12345);
        assertEquals(12345, unsafe.getInt(arr, index1));
    }
    
    @Test
    public void intField() {
        UnsafeObjectTest obj = new UnsafeObjectTest();
        long iOffset = objectFieldOffset("i");
        
        assertEquals(0, unsafe.getInt(obj, iOffset));
        unsafe.putInt(obj, iOffset, 12345);
        assertEquals(12345, unsafe.getInt(obj, iOffset));
    }
    
    @Test
    public void longArray() {
        long[] arr = {3, 4, 5};
        
        long longArrBaseOffset = unsafe.arrayBaseOffset(long[].class);
        long longArrIndexScale = unsafe.arrayIndexScale(long[].class);
        long index1 = longArrBaseOffset + longArrIndexScale;
        
        assertEquals(4L, unsafe.getLong(arr, index1));
        unsafe.putLong(arr, index1, 12345L);
        assertEquals(12345L, unsafe.getLong(arr, index1));
    }
    
    @Test
    public void longField() {
        UnsafeObjectTest obj = new UnsafeObjectTest();
        long jOffset = objectFieldOffset("j");
        
        assertEquals(0L, unsafe.getLong(obj, jOffset));
        unsafe.putLong(obj, jOffset, 12345L);
        assertEquals(12345L, unsafe.getLong(obj, jOffset));
    }
    
    private static long objectFieldOffset(String fieldName) {
        try {
            Field f = UnsafeObjectTest.class.getDeclaredField(fieldName);
            return unsafe.objectFieldOffset(f);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        UnitTestRunner.run(UnsafeObjectTest.class);
    }
    
}
