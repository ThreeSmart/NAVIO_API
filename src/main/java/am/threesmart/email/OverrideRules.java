package am.threesmart.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class OverrideRules implements Iterable<OverrideRules.SinglePair> {

    private final List<SinglePair> overrideRules = new ArrayList<>();

    public OverrideRules addRule(final String key, final String value) {
        overrideRules.add(new SinglePair(key, value));
        return this;
    }


    @Override
    public Iterator<SinglePair> iterator() {
        final int[] counter = {0};
        return new Iterator() {
            @Override
            public boolean hasNext() {
                try {
                    overrideRules.get(counter[0]);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public SinglePair next() {
                return overrideRules.get(counter[0]++);
            }
        };
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SinglePair {
        private String key;
        private String value;
    }

}
