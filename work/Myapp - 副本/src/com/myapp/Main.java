package com.myapp;

import com.myapp.util.CalculateUtil;
import com.myapp.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        switch (args.length) {
            case 0:
                System.out.println("请输入参数！");
                return;
            case 2:
                switch (args[0]) {
                    case "-n":
                        //指定题目数目
                        break;
                    case "-r":
                        //题目中数值（自然数、真分数和真分数分母）的范围
                        break;
                    case "-s":
                        //指定题目文件计算出答案文件
                        creatAnswerFile(args[1]);
                        break;
                    default:
                        System.out.println("请输入正确参数");
                }
                break;
            case 4:
                if ("-e".equals(args[0]) && "-a".equals(args[2])) {
                    exercisesCheck(args[1], args[3]);
                } else if ("-n".equals(args[0]) && "-r".equals(args[2])) {

                } else if ("-r".equals(args[0]) && "-n".equals(args[2])) {

                } else {
                    System.out.println("请输入正确参数");
                }
                break;
            default:
                System.out.println("请输入正确参数");
        }
        System.out.println("耗时" + (System.currentTimeMillis() - start) + "毫秒");

    }

    /**
     * 检验答案
     *
     * @param questionFile
     * @param answerFile
     */
    protected static void exercisesCheck(String questionFile, String answerFile) {
        List<Integer> Correct = new ArrayList<>();
        List<Integer> Wrong = new ArrayList<>();
        List<String>[] exercises = FileUtil.readQuestion(questionFile, answerFile);

        if (exercises == null) {
            return;
        }
        //题目
        String answerString;
        //答案
        String expression;
        //题目序号
        int i = 1;
        //两文件数目不对应时取最短数目文件为标准
        int length = exercises[0].size() < exercises[1].size() ? exercises[0].size() : exercises[1].size();
        for (int index = 0; index < length; index++) {
            //获取对应答案
            expression = exercises[0].get(index);
            answerString = exercises[1].get(index);
//            System.out.println(CalculateUtil.Calculate(expression));
            if (answerString.equals(CalculateUtil.Calculate(expression))) {
                Correct.add(i++);
            } else {
                Wrong.add(i++);
            }
        }
        System.out.println("Correct: " + Correct.size() + Correct);
        System.out.println("Wrong: " + Wrong.size() + Wrong);
    }

    /**
     * 根据题目文件生成答案
     *
     * @param fileName
     */
    public static void creatAnswerFile(String fileName) {
        List<String> questions = FileUtil.readFile(fileName);
        if (questions == null) {
            return;
        }
        List<String> answers = new ArrayList<>(questions.size());
        String answer;
        for (String question : questions) {
            answer = CalculateUtil.Calculate(question);
            if (answer == null) {
                answers.add("计算过程出现负数");
            } else {
                answers.add(answer);
            }
        }
        FileUtil.writeFile(answers, fileName.replaceFirst("\\.txt", "【答案】.txt"));
    }


}
