package com.zy17.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Created by zy17 on 2016/3/16.
 */
@Data
public class UserStatistics implements Serializable {
    public static final String STATISTICS = "statistics";
    public static final String CORRECT = "correct";
    public static final String WRONG = "wrong";
    private String statisticsKey;

    // 正确性
    private double accuracy;
    // 错误数
    private Map<Long, Integer> wrongCount;
    // 答对数
    private Map<Long, Integer> correctCount;
    // 历史回答id
    private List<Long> hisIds = new ArrayList<>();

    public void addGuessHis(Long id, boolean result) {
        int count = 0;

        if (result) {
            if (getCorrectCount().get(id) != null) {
                count = getCorrectCount().get(id);
            }
            count += 1;
            getCorrectCount().put(id, count);
        } else {
            if (getWrongCount().get(id) != null) {
                count = getWrongCount().get(id);
            }
            count += 1;
            getWrongCount().put(id, count);
        }
    }

    public Map<Long, Integer> getWrongCount() {
        if (wrongCount == null) {
            wrongCount = new HashMap<>();
        }
        return wrongCount;
    }

    public Map<Long, Integer> getCorrectCount() {
        if (correctCount == null) {
            correctCount = new HashMap<>();
        }
        return correctCount;
    }

    public int countWrong(Long id) {
        Integer integer = getWrongCount().get(id);
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    //    public double getAccuracy() {
    //        int total = correctCount.size() + wrongCount.size();
    //
    //        if (total != 0) {
    //            accuracy = correctCount.size() / total;
    //        } else {
    //            accuracy = 0;
    //        }
    //        return accuracy;
    //    }

}
