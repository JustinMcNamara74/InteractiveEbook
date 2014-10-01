package beans;

import data.AccessDB;
import data.CheckAnswer;
import data.QuizQuestion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

// Just made it SessionScoped because it solves all problems.
@Named
@SessionScoped
public class QuizBean implements Serializable {

    AccessDB db = AccessDB.getInstance();

    @Inject
    private UserBean userBean;
    
    private String answer;
    private List<String> answerList;
    
    /**
     * Creates a new instance of QuizBean
     */
    public QuizBean() {
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void submit() {
        if (answer.equals("")) {
            return;
        }
        String[] s = answer.split(" ");
        boolean rW = CheckAnswer.checkAnswer(s[0], s[1]);
        //r for 'right' and w for 'wrong'
        if (rW) {
            CheckAnswer.setAnswer('r', userBean.getUserName(), s[0]);
        } else {
            CheckAnswer.setAnswer('w', userBean.getUserName(), s[0]);
        }
    }

    public void multiSubmit() {
        if (!answerList.isEmpty()) {
            String[] s = answerList.get(0).split(" ");
            String[] s1;
            String str;
            StringBuilder sb = new StringBuilder();
            for (String st : answerList) {
                s1 = st.split(" ");
                sb.append(s1[1]);
            }
            boolean rW = CheckAnswer.checkAnswer(s[0], sb.toString());

            if (rW) {
                CheckAnswer.setAnswer('r', userBean.getUserName(), s[0]);
            } else {
                CheckAnswer.setAnswer('w', userBean.getUserName(), s[0]);
            }
        }
    }
    
    public List<QuizQuestion> lookupQuestionsByChapter(int chapter) {
        List<QuizQuestion> questionList = new ArrayList<>();
        
        List<String> questionData = db.query("select chapter, section, number, questiontext, "
                +" multiplechoice from quizquestions where chapter = "
                + chapter+" order by number");
        
        for(String qd : questionData) {
            QuizQuestion qq = new QuizQuestion(qd);
            qq.setAnswers(db.query("select * from quizanswers where chapter = "+chapter+" and number = "+qq.getNumber()+" order by answer"));
            questionList.add(qq);
        }
        
        return questionList;
    }

    public List<QuizQuestion> lookupQuestionsBySection(int chapter, int section) {
        List<QuizQuestion> questionList = new ArrayList<>();
        
        List<String> questionData = db.query("select chapter, section, number, questiontext, "
                +" multiplechoice from quizquestions where chapter = "
                + chapter +" and section = " + section+" order by number");
        
        for(String qd : questionData) {
            QuizQuestion qq = new QuizQuestion(qd);
            qq.setAnswers(db.query("select * from quizanswers where chapter = "+chapter+" and number = "+qq.getNumber()+" order by answer"));
            questionList.add(qq);
        }
        
        return questionList;
    }
    
    public QuizQuestion lookupQuestion(int chapter, int section, int number) {
        QuizQuestion returnQuestion = null;
        
        List<String> questionData = db.query("select chapter, section, number, questiontext, "
                +" multiplechoice from quizquestions where chapter = "
                + chapter +" and section = " + section+" and number = " + number);
        
        if(questionData.size() > 0) {
            returnQuestion = new QuizQuestion(questionData.get(0));
            returnQuestion.setAnswers(db.query("select * from quizanswers where chapter = "+chapter+" and number = "+returnQuestion.getNumber()+" order by answer"));
        }
        
        return returnQuestion;
    }
    
    /**
     * @return the answerList
     */
    public List<String> getAnswerList() {
        return answerList;
    }

    /**
     * @param answerList the answerList to set
     */
    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }
    
    public boolean checkAnswer(int chapter, int number, String answer) {
        return checkAnswer(chapter, number, (List<String>)Collections.singleton(answer));
    }
    
    public boolean checkAnswer(int chapter, int number, List<String> answers) {
        
        if(answers != null && answers.size() > 0) {
            String answersCSV = String.join(", ", answers);

            List<String> correctAnswers = db.query("select answer from quizanswers where chapter = "+chapter
                +" and number = "+number+" and iscorrect = 1;");

            if (correctAnswers.size() == answers.size()) {
                for(int i=0; i < correctAnswers.size(); i++) {
                    if(!answers.contains(correctAnswers.get(i).split(AccessDB.DELIMETER)[0])) {
                        return false;
                    }
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public boolean submitAnswer(int chapter, int number, List<String> answers, UserBean user) {
        
        boolean isCorrect = checkAnswer(chapter, number, answers);
        
        if(answerStatus(chapter, number, user.getUserName()) == QuizQuestion.UNANSWERED) {
            db.update("insert into useranswers(username, chapter, number, correct)"
                    +" values('"+user.getUserName()+"', "+chapter+", "+number+", "
                    +(isCorrect? "1" : "0")+");");
        }
        else {
            db.update("update useranswers set correct = "+(isCorrect? "1" : "0")
                    +" where username = '"+user.getUserName()+"' and chapter = "+chapter+" and number = "+number+";");
        }
        
        return isCorrect;
    }
    
    public int answerStatus(Integer chapter, Integer number, String username) {
        
        List<String> answers = db.query("select correct from useranswers where username = '"
            + username + "' and chapter = " + chapter + " and number = "+number+";");
        
        if(!answers.isEmpty()) {
            String isCorrect = answers.get(0).split(AccessDB.DELIMETER)[0];
            
            switch (isCorrect) {
                case "1":
                    return QuizQuestion.CORRECT;
                default:
                    return QuizQuestion.INCORRECT;
            }
        }
        
        return QuizQuestion.UNANSWERED;
    }
    
}
