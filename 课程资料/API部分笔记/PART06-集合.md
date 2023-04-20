#  集合

## Collection接口

### 概述

- 数组和集合都是Java中提供的容器

- 集合: 英文译为 Collection，用来存放对象的容器，集合中可以存放不同类型的对象，并且集合的长度可变。在编程时，常常需要集中存放多个数据，可以使用数组来保存多个对象，但数组长度不可变化，一旦在初始化数组时指定了数组长度，这个数组长度就是不可变的，如果需要保存数量变化的数据，数组就有点无能为力了；为了保存数量不确定的数据，以及保存具有映射关系的数据，Java提供了集合类。集合类主要负责保存、盛装其他数据，因此集合类也被称为容器类。
- 集合和数组的对比:
  - 数组中的元素可以基本类型的值，也可以是对象; 而集合中只能保存对象
  - 数组一旦指定了长度，长度就不能再改变; 而集合的长度是可以随时改变的
  - 往数组中插入元素非常麻烦,需要将插入位置后面的元素往后移动; 或者删除数组中间位置的某一个元素, 需要将删除位置后的元素往前移动; 而如果往集合中插入元素或者删除集合中的某一个元素,直接使用现成的方法操作即可

### 集合的继承结构

- 由于需求不同，Java就提供了不同的集合类。这多个集合类的数据结构不同，但是它们都是要提供存储和遍历功能的，我们把它们的共性不断的向上提取，最终就形成了集合的继承体系结构图。

- Collection接口
  - List接口
    - ArrayList类
    - LinkedList类
  - Set接口
    - HashSet类
    - TreeSet类

- 解释说明:
  - Collection集合是所有单值集合的顶层接口, 其中定义了常用的用于操作集合以及集合中元素的方法例如: 添加元素、删除元素、获取元素、获取集合长度、判断功能、将集合转成数组、迭代器遍历元素等功能
  - List是Collection的子接口，特点是其中的元素是有序的(即:元素存入集合时的顺序和取出的顺序一致)可以通过下标访问List中的元素，另,List集合中的元素是可以重复的(包括null)
  - Set也是Collection的子接口，特点是其中的元素是无序(即:元素存入集合时的顺序和取出的顺序不一定一致)无法通过下标访问Set中的元素，另外,Set集合中的元素是不可以重复的

- 学习集合的建议:
  - 学习接口中提供的共性方法
  - 通过实现类创建对象, 调用这些共性方法

### 常用方法

```
boolean add(E e) 往集合中添加指定元素e
boolean addAll(Collection c) 将小集合添加到大集合中
boolean isEmpty() 如果集合中没有任何元素(空集合), 返回true
boolean contains(Object o) 如果此集合中包含指定元素o, 则返回true
boolean containsAll(Collection c) 如果此集合中包含指定 集合c 中的所有元素，则返回 true。 
int size() 返回集合的大小(元素个数)
boolean remove(Object o) 从集合中删除指定的元素o, 删除成功则返回true
boolean removeAll(Collection c) 删除此集合中那些也包含在指定集合c中的所有元素
boolean retainAll(Collection c) 仅保留此集合中那些也包含在指定集合c中的元素 
c1.retainAll(c2) 只保留c1中两个共同的元素 "a","b", 对c2没有影响
void clear() 删除此集合中的所有元素
Object[] toArray() 将此集合转成对象数组
boolean equals(Object o) 比较此 collection 与指定对象是否相等。 
Iterator<E> iterator() 返回此集合中所有元素组成的迭代器。
```

### 实例1：CollectionDemo1

```java

```

### 实例2：CollectionDemo2

```java

```

#### Point

```java

```

### 实例3：CollectionDemo3

```java

```

### 实例4：CollectionDemo4

```java

```

## 集合的遍历

### IteratorDemo

 

```java

```

## 增强型for循环

### NewForDemo

```java

```

## List集合

### 概述

- List是一个有序的Collection(List是Collection的子接口)，使用此接口能够精确的控制每个元素插入的位置，能够通过索引(类似于数组的下标)来访问List中的元素，第一个元素的索引为 0，而且允许有相同的元素。

- List 接口存储一组可重复、有序（插入顺序）的对象。

### 特点

- 元素有下标,可以通过下标访问元素

- 元素是有序的(存入集合的顺序和取出的顺序一定相同)

- 元素可以重复(包括null)

### List方法测试

#### ListDemo

```java

```

#### ListDemo2

```java

```

#### ListDemo3

```java

```

### 集合和数组的转换

#### 集合转换数组:CollectionToArrayDemo

```java

```

#### 数组转换集合: ArrayToListDemo

```java

```

### 集合的排序

#### SortListDemo

```java

```

#### SortListDemo2

```java

```

#### SortListDemo3

```java

```

## Map接口

### 概述

- Map用于保存具有映射关系的数据，因此Map集合里保存着两组值，一组值用于保存Map里的键(key)另外一组值用于保存Map里的值(value)，键和值是一一对应的关系，称为映射。根据键就能找到对应的值，类似于生活中一张身份证对应一个人一样。

- Map的key和value可以是任何引用类型的数据，其中key不允许重复，同一个Map对象的任何两个key通过equals方法比较总是返回false。

### 特点

- Map集合中每个元素都有两个值, 分别是key(键) 和 value(值)
- Map集合中的key(键)不允许重复, 在第二次添加已有的key时, value会被会覆盖
- Map集合中的元素是无序的(即元素存入集合的顺序和取出时的顺序很可能不一致)
- Map集合中的key和value具有映射关系, 可以通过key(键)来获取对应的value(值)
  - key和value之间存在单向一对一关系，即通过指定的key，总能找到唯一的、确定的value。从Map中取出数据时，只要给出指定的key，就可以取出对应的value。
  - 如果把Map里的所有key放在一起来看，它们就组成了一个Set集合（所有的key没有顺序，key与key之间不能重复），实际上Map确实包含了一个keySet()方法，用于返回Map里所有key组成的Set集合。
  - 如果把Map里的所有value放在一起来看，它们又非常类似于一个List：元素与元素之间可以重复，每个元素可以根据索引来查找，只是Map中的索引(也就是key)不是从0开始的整数值，而是任意类型的对象；
  - 如果需要从List集合中取出元素，则需要提供该元素的数字索引；如果需要从Map中取出元素，则需要提供该元素的key。因此，Map有时也被称为字典，或关联数组。

### 继承结构

- Map接口
  - HashMap类
  - TreeMap类
  - Hashtable类

- 解释说明:
  - Map集合是采用键-值对(key-value)的存储方式, 键(key)、值(value)可以是引用类型的数据, key不允许重复, vaue可以重复, key和value是一对一的关系, 通过指定的key总能找到唯一的、确定的value值
  - HashMap 和 Hashtable 都是Map接口的实现类，它们之间的关系完全类似于ArrayList和Vector的关系 
    - HashMap是线程不安全的, 所以HashMap的性能要比HashTable高一些
    - HashMap可以使用null作为key或value, Hashtable不允许使用null作为key和value; 
    - Hashtable是一个古老的Map实现类，JDK 1.0出现，出现时，Java还没有提供Map接口，命名没有遵守Java的命名规范，与Vector类似的是，尽量少用Hashtable实现类，即使需要创建线程安全的Map实现类，也无须使用Hashtable实现类，可以通过别的方式来解决线程安全问题。
  - TreeMap是Map的子接口SortedMap的的实现类, 是可以支持对内部元素进行排序的类, 也正因为如此, TreeMap的执行效率通常要比HashMap和HashTable慢。

### MapDemo

```java

```

### MapDemo2

```java

```
