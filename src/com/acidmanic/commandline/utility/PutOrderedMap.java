/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author diego
 */
public class PutOrderedMap<TKey, TValue> implements Map<TKey, TValue> {

    private ArrayList<TKey> keys;
    private ArrayList<TValue> values;

    public PutOrderedMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();

    }

    @Override
    public int size() {
        return this.keys.size();
    }

    @Override
    public boolean isEmpty() {
        return this.keys.isEmpty();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean containsKey(Object key) {
        return this.keys.contains(key);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean containsValue(Object value) {
        return this.values.contains(value);
    }

    @SuppressWarnings("element-type-mismatch")
    private int indexOf(Object key) {

        if (containsKey(key)) {
            for (int i = 0; i < this.keys.size(); i++) {
                if (keys.get(i).equals(key)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public TValue get(Object key) {

        int i = indexOf(key);

        if (i != -1) {
            return this.values.get(i);
        }
        return null;
    }

    @Override
    public TValue put(TKey key, TValue value) {

        int index = indexOf(key);

        if (index != -1) {
            this.keys.remove(index);
            this.values.remove(index);
        }

        this.keys.add(key);

        this.values.add(value);

        return value;
    }

    @Override
    public TValue remove(Object key) {

        int index = indexOf(key);

        if (index != -1) {
            TValue value = this.values.get(index);

            this.keys.remove(index);
            this.values.remove(index);

            return value;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends TKey, ? extends TValue> m) {

        m.forEach((k, v) -> this.put(k, v));
    }

    @Override
    public void clear() {
        this.keys.clear();
        this.values.clear();
    }

    @Override
    public Set<TKey> keySet() {

        LinkedHashSet<TKey> keyset = new LinkedHashSet<>();

        this.keys.forEach(k -> keyset.add(k));

        return keyset;
    }

    @Override
    public Collection<TValue> values() {

        ArrayList<TValue> valuesList = new ArrayList<>(this.values);

        return valuesList;
    }

    private class SolidEntry implements Entry<TKey, TValue> {

        private final TKey key;
        private TValue value;

        public SolidEntry(TKey key, TValue value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public TKey getKey() {
            return key;
        }

        @Override
        public TValue getValue() {
            return value;
        }

        @Override
        public TValue setValue(TValue value) {
            this.value = value;
            return value;
        }

    }

    @Override
    public Set<Entry<TKey, TValue>> entrySet() {

        LinkedHashSet<Entry<TKey, TValue>> entries = new LinkedHashSet<>();

        for (int i = 0; i < this.keys.size(); i++) {
            entries.add(new SolidEntry(
                    this.keys.get(i),
                    this.values.get(i)
            ));
        }

        return entries;
    }

}
