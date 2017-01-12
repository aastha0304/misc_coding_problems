#include <cstdio>
#include <iostream>
using namespace std;
void display(int** a, int M){
    for(int i=0;i<M;i++){
        for(int j=0;j<M;j++){
            cout<<a[i][j]<<" ";
        }
        cout<<endl;
    }
}
int dfs(int** g, int s, int e, int M, int *v){
    if(v[s]==1)
        return 0;
    else if(s==e){
        /*for(int i = 0;i<=e;i++){
            if(v[i]==1 || i==e)
                cout<<i<<" ";
        }
        cout<<'\n';*/
        return 1;
    }
    v[s]=1;
    int res = 0;
    for(int i =0;i<M;i++){
        if(g[s][i]==1 and v[i]==0){
            res += dfs(g, i, e, M, v);
        }
    }
    v[s]=0;
    return res;
}
int traversal_graph(int** g, int m, int s, int e){
    int M=m*m;
    int* v = new int[M] ();
    return dfs(g, s, e, M, v);
}
int** create_mat_graph(int m){
    int M = m*m;
    int** a = new int*[M];
    for(int i = 0; i < M; ++i){
        a[i] = new int[M];
        for(int j = 0;j<M;j++)
            a[i][j]=0;
    }
    for(int i=0;i<M;i++){
            if(i+m<M)
                a[i][i+m]=1;
            if(i-m>=0)
                a[i][i-m]=1;
            if(i%m!=0 && i%m!=(m-1)){
                if(i>0)
                    a[i][i-1]=1;
                if(i<M-1)
                    a[i][i+1]=1;
            }else if(i%m==0){
                if(i<M-1)
                    a[i][i+1]=1;
            }else{
                if(i>0)
                    a[i][i-1]=1;
            }
    }
    //display(a, M);
    return a;
}
int main(){
    int m = 4;
    int** g = create_mat_graph(m);
    cout<<traversal_graph(g, m, 0, m*m-1);
    return 0;
}