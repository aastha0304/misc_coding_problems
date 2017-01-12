#include <iostream>
#include <deque>
using namespace std;
struct node{
    int v;
    struct node* left;
    struct node* right;
};
void inorder(struct node *root){
    if(!root)
        return;
    inorder(root->left);
    cout<<root->v<<' ';
    inorder(root->right);
}
void insert(struct node **root, int n){
    if(*root==NULL){
        *root = (struct node *)malloc(sizeof(struct node));
        (*root)->v=n;
        (*root)->left=(*root)->right=NULL;
    }
    else if(n>(*root)->v)
        insert(&((*root)->right), n);
    else
        insert(&((*root)->left), n);
}
void bst2dll(struct node* root, struct node** head, struct node** prev){
    if(root==NULL)
        return;
    bst2dll(root->left, head, prev);
    if ((*prev) == NULL)
        *head = root;
    else
    {
        root->left = *prev;
        (*prev)->right = root;
    }
    *prev = root;
    bst2dll(root->right, head, prev);
}
void traversedll(struct node *node)
{
    while (node!=NULL)
    {
        cout << node->v << " ";
        node = node->right;
    }
}
void levelorder(struct node* root){
    if(!root)
        return;
    deque<struct node*> q;
    q.push_back(root);
    struct node* tmp = (struct node *)malloc(sizeof(struct node));
    tmp->v=-1;
    tmp->left=tmp->right=NULL;
    q.push_back(tmp);
    while(!q.empty()){
        struct node* tp = q.front();
        if(tp->v==-1){
            q.pop_front();
            if(!q.empty()){
                q.push_back(tmp);
                cout<<'\n';
             }else
                return;
        }else{
            cout<<tp->v<<' ';
            if(tp->left)
                q.push_back(tp->left);
            if(tp->right)
                q.push_back(tp->right);
            q.pop_front();
        }

    }
}
void leftside(struct node* root){
    if(root==NULL)
        return;
    cout<<root->v<<' ';
    if(root->left!=NULL)
        leftside(root->left);
    else if(root->right!=NULL)
        leftside(root->right);
    else
        return;

}
int main(){
    struct node* root=NULL;
    insert(&root, 8);
    insert(&root, 9);
    insert(&root, 7);
    insert(&root, 8);
    insert(&root, 7);
    insert(&root, 5);
    insert(&root, 4);
    insert(&root, 1);
    insert(&root, 8);
    insert(&root, 3);
    //levelorder(root);
    leftside(root);
    //inorder(root);
    cout<<'\n';
    struct node* head = NULL;
    struct node* prev = NULL;
    //bst2dll(root, &head, &prev);
    //traversedll(head);
    return 0;
}