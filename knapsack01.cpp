#include <iostream>
#include <fstream>
#include <string>
#include <string>
#include <sstream>
#include <vector>
#include <cmath>
using namespace std;
struct pkg
{
    int i;
    int w;
    float wr;
    float c;
};
typedef struct pkg pkgt;
string trim(string& str)
{
    size_t first = str.find_first_not_of(' ');
    size_t last = str.find_last_not_of(' ');
    return str.substr(first, (last-first+1));
}
vector<string> &split(const string &s, char delim, vector<string> &elems) {
    stringstream ss(s);
    string item;
    while (getline(ss, item, delim)) {
        elems.push_back(item);
    }
    return elems;
}
vector<string> split(const string &s, char delim) {
    vector<string> elems;
    split(s, delim, elems);
    return elems;
}
float max(float a, float b){
    return a>b?a:b;
}
void solveKnap(int K, vector<pkgt> plist, int n){
    int i, w;
    float a[n+1][K+1];
    int idx[n][K+1];
    for(i=0;i<=n;i++){
        for(w=0;w<=K;w++){
            idx[i][w]=0;
            if(i==0 || w==0)
                a[i][w]=0.0;
            else if(plist[i-1].wr<w){
                a[i][w]=max(plist[i-1].c+a[i-1][w-((plist[i-1]).w)], a[i-1][w]);
                if((a[i][w])==plist[i-1].c+a[i-1][w-((plist[i-1]).w)])
                    idx[i-1][w]=1;
            }
            else
                a[i][w]=a[i-1][w];
        }
    }
    if(a[n][K]==0){
        cout<<'-'<<'\n';
        return;
    }
    w=K;
    for(int i=n;i>=1;i--){
        if(idx[i][w]==1){
            cout<<i+1<<",";
            w-=plist[i].w;
        }
    }
}
void parse(string line){
    vector<string> a = split(line, ':');
    int K = stoi(trim(a[0]));
    vector<string> b = split(a[1], '(');
    int n = b.size()-1;
    vector<pkgt> plist;
    int j = 0;
    for(vector<int>::size_type i = 1; i < n+1; i++) {
        if(!b[i].empty()){
            vector<string> c = split(b[i], ',');
            if(c.size()==3){
                int idx = atoi(trim(c[0]).c_str());
                float w = atof(trim(c[1]).c_str());
                string tmp = trim(c[2]);
                tmp = tmp.substr(1, tmp.size()-1);
                float cs = atof(tmp.c_str());
                pkgt pob;
                pob.i = idx;
                pob.w = (int)round(w);
                pob.wr = w;
                pob.c = cs;
                plist.push_back(pob);
                j++;
            }
        }
    }
    solveKnap(K, plist, j);
    cout<<endl;
}
int main(){
    string line;
    ifstream myfile ("example.txt");
    if (myfile.is_open())
        {
        while(getline(myfile,line))
        {
          parse(line);
        }
        myfile.close();
    }
    return 0;
}