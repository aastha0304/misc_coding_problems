package CommentsService;

import java.util.LinkedList;
import java.util.List;

public class CommentsOps {
    public List<CommentsBean> getCommentsList(long articleId){
        List<CommentsBean> allComments = new LinkedList<>();
        //fetch from where?
        return allComments;
    }
    public CommentsBean getCommentsDetails(long commentId){
        CommentsBean res = new CommentsBean();//fetch from where
        return res;
    }
    public void postAComment(CommentsBean comment){
        //post where?
    }
    public void renderComments(long articleId){
        //List<CommentsBean> = getCommentsList(articleId);

    }
}
