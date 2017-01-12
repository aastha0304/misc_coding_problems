#include <iostream>
#include <vector>
#include <deque>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
typedef struct TreeNode tree;
class BSTIterator {
    tree* tr;
    vector<int> res;
    int i;
    int k;
    void tr_arr(tree* tr){
        if(!tr)
            return;
        tr_arr(tr->left);
        (this->res).push_back(tr->val);
        (this->i)++;
        tr_arr(tr->right);
    }
public:
    BSTIterator(TreeNode *root) {
        this->tr = root;
        this->i=0;
        tr_arr(root);
        this->k = 0;
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        if(this->k<this->i)
            return true;
        return false;
    }

    /** @return the next smallest number */
    int next() {
        int ret = (this->res)[k];
        (this->k)++;

        return ret;
    }
};
tree* create_tree(int v){
    tree* tr=new tree(v);
    tr->val = v;
    tr->left = NULL;
    tr->right = NULL;
    return tr;
}
void insert(tree **tr, int v){
    if(*tr==NULL){
        tree* t=create_tree(v);
        *tr = t;
    }
    else if((*tr)->val< v)
        insert(&((*tr)->right), v);
    else
        insert(&((*tr)->left), v);
}
void print_in(tree* tr){
    if(!tr)
        return;
    print_in(tr->left);
    cout<<tr->val<<endl;
    print_in(tr->right);
}
int main(){
    tree *tr = create_tree(9);
    insert(&tr, 10);

    insert(&tr, 4);
    insert(&tr, 3);

    insert(&tr, 8);
    insert(&tr, 10);
    insert(&tr, 16);

    insert(&tr, 12);
    insert(&tr, 18);
    BSTIterator b(tr);
    while(b.hasNext()){
        cout<<b.next()<<endl;
    }

}