package personal.pengfeil.leetcodeoj;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by pengfeil on 11/24/16.
 */
public class LFUCache {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(10);
//        cache.set(2, 1);
//        cache.set(1, 1);
//        cache.set(2, 3);
//        cache.set(4, 1);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));
//
        cache.set(10, 13);
        cache.set(3, 17);
        cache.set(6, 11);
        cache.set(10, 5);
        cache.set(9, 10);
        System.out.println(cache.get(13));
        cache.set(2, 19);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.set(5, 25);
        System.out.println(cache.get(8));
        cache.set(9, 22);
        cache.set(5, 5);
        cache.set(1, 30);
        System.out.println(cache.get(11));
        cache.set(9, 12);
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        System.out.println(cache.get(8));
        System.out.println(cache.get(9));

//        10-1, 3-0, 6-0,

        cache.set(4, 30);
        cache.set(9, 3);
        System.out.println(cache.get(9));
        System.out.println(cache.get(10));
        System.out.println(cache.get(10));
        cache.set(6, 14);
        cache.set(3, 1);
        System.out.println(cache.get(3));
        cache.set(10, 11);
        System.out.println(cache.get(8));
        cache.set(2, 14);
        System.out.println(cache.get(1));
        System.out.println(cache.get(5));
        System.out.println(cache.get(4));
        cache.set(11, 4);
        cache.set(12, 24);
        cache.set(5, 18);
        System.out.println(cache.get(13));
        cache.set(7, 23);
        System.out.println(cache.get(8));
        System.out.println(cache.get(12));
        cache.set(3, 27);
        cache.set(2, 12);
        System.out.println(cache.get(5));
        cache.set(2, 9);
        cache.set(13, 4);
        cache.set(8, 18);

        cache.set(1, 7);
        System.out.println(cache.get(6));
        cache.set(9, 29);
        cache.set(8, 21);
        System.out.println(cache.get(5));
        cache.set(6, 30);
        cache.set(1, 12);
        System.out.println(cache.get(10));
    }

    private long capacity;

    private Map<Integer, Entity> entryMap = new HashMap<>();

    private FrequencyNode frequencyListHead = new FrequencyNode(-1);

    private Map<Long, FrequencyNode> frequencyNodeMap = new HashMap<>();

    public LFUCache(long size) {
        this.capacity = size;
        frequencyNodeMap.put(-1L, frequencyListHead);
    }

    public int get(int key) {
        if (!entryMap.containsKey(key)) {
            return -1;
        }

        Entity targetEntity = entryMap.get(key);

        increaseFrequency(targetEntity);

        return targetEntity.value;
    }

    private void increaseFrequency(Entity targetEntity) {
        FrequencyNode frequencyNode = frequencyNodeMap.get(targetEntity.frequency);
        targetEntity.frequency++;
        if (targetEntity.pre != null) {
            // Remove from current list
            targetEntity.pre.next = targetEntity.next;
            targetEntity.next.pre = targetEntity.pre;
        }
        //Insert into next frequency list
        //Handle frequency node
        if (frequencyNode.next == null) {
            frequencyNode.next = createFrequencyNode(targetEntity.frequency);
            frequencyNode.next.pre = frequencyNode;
        } else if (frequencyNode.next.frequency != targetEntity.frequency) {
            FrequencyNode newNode = createFrequencyNode(targetEntity.frequency);
            newNode.next = frequencyNode.next;
            newNode.pre = frequencyNode;
            newNode.next.pre = newNode;
            frequencyNode.next = newNode;
        }

        targetEntity.next = frequencyNode.next.entityListHead.next;
        targetEntity.pre = frequencyNode.next.entityListHead;
        if (targetEntity.next != null) {
            targetEntity.next.pre = targetEntity;
        }
        frequencyNode.next.entityListHead.next = targetEntity;

        // Delete empty frequency list
        if (frequencyNode != frequencyListHead) {
            // Don't delete head
            deleteEmptyFrequencyNode(frequencyNode);
        }
    }

    private void deleteEmptyFrequencyNode(FrequencyNode frequencyNode) {
        if (frequencyNode.entityListHead.next == frequencyNode.entityListTail) {
            // Remove empty frequency Node
            if (frequencyNode.next != null) {
                frequencyNode.next.pre = frequencyNode.pre;
            }
            frequencyNode.pre.next = frequencyNode.next;
        }
    }

    private FrequencyNode createFrequencyNode(long frequency) {
        FrequencyNode newNode = new FrequencyNode(frequency);
        newNode.entityListHead = new Entity();
        newNode.entityListTail = new Entity();
        newNode.entityListHead.next = newNode.entityListTail;
        newNode.entityListTail.pre = newNode.entityListHead;
        frequencyNodeMap.put(frequency, newNode);
        return newNode;
    }

    public void set(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (entryMap.containsKey(key)) {
            Entity targetEntity = entryMap.get(key);
            targetEntity.value = value;
            increaseFrequency(targetEntity);
        } else {
            Entity newEntity = new Entity();
            newEntity.key = key;
            newEntity.value = value;
            newEntity.frequency = -1;
            if (entryMap.size() >= capacity) {
                // Delete invalid entity
                FrequencyNode leastFrequencyNode = frequencyListHead.next;
                Entity invalidEntity = leastFrequencyNode.entityListTail.pre;
                entryMap.remove(invalidEntity.key);
                invalidEntity.pre.next = invalidEntity.next;
                invalidEntity.next.pre = invalidEntity.pre;
                deleteEmptyFrequencyNode(leastFrequencyNode);
            }
            // Insert new node and increase
            increaseFrequency(newEntity);
            // Put into cache
            entryMap.put(key, newEntity);
        }
    }

    private class Entity {
        public int key;
        public int value;
        // pointers in frequency list
        public Entity next;
        public Entity pre;
        public long frequency;
    }

    private class FrequencyNode {
        public FrequencyNode next;
        public FrequencyNode pre;
        public long frequency;
        public Entity entityListHead;
        public Entity entityListTail;

        public FrequencyNode(long frequency) {
            this.frequency = frequency;
        }
    }
}
