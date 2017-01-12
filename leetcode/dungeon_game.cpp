#include <iostream>
#include <vector>
#include <deque>
using namespace std;
class Solution {
    int min(int a, int b){
        return (a>b)?b:a;
    }
public:
    vector<vector<int> > convrt_vc(int m, int n, int a[][2]){
       vector<vector<int> > res;
       for(int i =0;i<m;i++){
        vector<int> k;
        for(int j=0;j<n;j++){
            k.push_back(a[i][j]);
        }
        res.push_back(k);
       }
       return res;
    }
    int calculateMinimumHP(vector<vector<int> > &dungeon) {
        int m = dungeon.size();
        int n;
        if(m>0)
            n = dungeon[0].size();
        if(m==1 && n==1)
            return dungeon[0][0]<0?dungeon[0][0]*-1+1:1;
        vector<vector<int> > a(m, vector<int>(n,0));
        int i,j;
        a[m-1][n-1] = dungeon[m-1][n-1]>0?1:dungeon[m-1][n-1]*-1+1;
        //cout<<m-1<<' '<<n-1<<' '<<a[m-1][n-1]<<endl;
        for(i=m-2;i>=0;i--){
            if(dungeon[i][n-1] >0){
                int s = dungeon[i][n-1]-a[i+1][n-1];
                if(s>0)
                    a[i][n-1] = 0;
                else
                    a[i][n-1] = s*-1;
            }
            else{
                int s = a[i+1][n-1];;
                if(s>0)
                    a[i][n-1] = dungeon[i][n-1]*-1+s;
                else
                    a[i][n-1] = dungeon[i][n-1]*-1+1;
            }
            //cout<<i<<' '<<n-1<<' '<<a[i][n-1]<<endl;
        }
        for(j=n-2;j>=0;j--){
            if(dungeon[m-1][j]>0){
                int s = dungeon[m-1][j]-a[m-1][j+1];
                //cout<<s<<endl;
                if(s>0)
                    a[m-1][j] = 0;
                else
                    a[m-1][j] = s*-1;
            }
            else{
                int s = a[m-1][j+1];
                cout<<s<<endl;
                if(s>0)
                    a[m-1][j] = dungeon[m-1][j]*-1+s;
                else
                    a[m-1][j] = dungeon[m-1][j]*-1+1;
            }
            //cout<<m-1<<' '<<j<<' '<<a[m-1][j]<<endl;
        }
        for(i=m-2;i>=0;i--){
            for(j=n-2;j>=0;j--){
                int s = min(a[i+1][j], a[i][j+1]);
                //cout<<s<<endl;
                if(dungeon[i][j]>0){
                    int d = dungeon[i][j]-s;
                    if(d>0)
                        a[i][j] = 0;
                    else
                        a[i][j] = d*-1;
                }
                else{
                    if(s>0)
                        a[i][j] = (dungeon[i][j]*-1)+s;
                    else
                        a[i][j] = (dungeon[i][j]*-1)+1;
                }
                //cout<<i<<' ' <<j<<' '<<a[i][j]<<endl;
            }
        }

        return a[0][0]>0?a[0][0]:1;
    }
};
int main(){
    //int a[3][3] = {{-2, -3, 3},{-5, -10, 1},{10, 30, -5}};
    int a[1][2] = {{0,-3}};
    Solution s;
    vector<vector<int> > vec = s.convrt_vc(1, 2, a);
    int res = s.calculateMinimumHP(vec);
    cout<<res;
}