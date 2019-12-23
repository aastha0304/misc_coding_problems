package CommentsService;

public class CommentsBean {
    //ignored UUID for creating separatable comments
    private long commentsId;
    private long articleId;
    private long commentorId;
    private long parentId;
    private String commentBody;
    private long createdTS;
    private long updatedTS;

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public long getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(long commentsId) {
        this.commentsId = commentsId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getCommentorId() {
        return commentorId;
    }

    public void setCommentorId(long commentorId) {
        this.commentorId = commentorId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(long createdTS) {
        this.createdTS = createdTS;
    }

    public long getUpdatedTS() {
        return updatedTS;
    }

    public void setUpdatedTS(long updatedTS) {
        this.updatedTS = updatedTS;
    }
}
