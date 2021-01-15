package graph;

import java.util.Map;

public class MyEntry<K,V> implements Map.Entry<K,V>{

    K key;
    V value;

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V v) {
        V olderValue = this.value;
        this.value = v;
        return olderValue;
    }

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object object){
        if(this == object)return true;
        if(object == null || object.getClass()!=this.getClass())return false;
        MyEntry myEntry = (MyEntry) object;
        return myEntry.key == this.key && myEntry.value == this.value;
    }

    @Override
    public int hashCode(){
        return key.hashCode()+value.hashCode();
    }


}
