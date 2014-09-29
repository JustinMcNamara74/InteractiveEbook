package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import data.AccessDB;
import data.CheckAnswer;
import data.Populate;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Slash
 */
@ManagedBean
@ApplicationScoped
public class QuizBean {

    AccessDB db = AccessDB.getInstance();

    private String answer;
    private String problem;
    private String user = "buttcheeks1";
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
            CheckAnswer.setAnswer('r', user, s[0]);
        } else {
            CheckAnswer.setAnswer('w', user, s[0]);
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
                CheckAnswer.setAnswer('r', user, s[0]);
            } else {
                CheckAnswer.setAnswer('w', user, s[0]);
            }
        }
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
    
    public boolean isRight(String id){
       
        String st = db.query("select rightwrong from answered where username = '"
            + user + "' and problem = '" + id + "';");
        
        String stArr = st.substring(0,1);
        
        System.out.println(stArr);
        return stArr.equals("r");
        
    }
    
    public boolean isWrong(String id){
        
        String st = db.query("select rightwrong from answered where username = '"
            + user + "' and problem = '" + id + "';");
        String stArr = st.substring(0,1);
        
        return stArr.equals("w");
        
    }
    
    public boolean isUnanswered(String id){
              
        String st = db.query("select rightwrong from answered where username = '"
            + user + "' and problem = '" + id + "';");
        
        String stArr = st.substring(0,1);
        return stArr.equals("u");
        
    }

}
