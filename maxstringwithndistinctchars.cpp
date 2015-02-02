#include <cstdio>
#include <iostream>
#include <string>
#include <deque>
using namespace std;
void max_stream(string s, int n){
    int i=0,items_in_q=0, maxl = 0, ql = 0;
    int in_q[256] =  {0};
    deque<char> dq;
    //abbabccaabaaaa
    while(s[i]!='\0'){
      char c = s[i];
      dq.push_back(c);
      ql++;
      if(in_q[c]){
            in_q[c]+=1;

      }
      else{
          items_in_q++;
          in_q[c]+=1;
          if(items_in_q <= n){
          }
          else{
            while(!dq.empty() && items_in_q > n){
              char ct = dq.front();
              dq.pop_front();
              ql--;
              in_q[ct]-=1;
              if(in_q[ct] == 0){
                      items_in_q--;
              }
            }
          }
      }
      if(maxl<ql){
          maxl = ql;
          cout<<"max len "<<maxl<<'\n';
          for(int j =0;j<ql;j++)
            cout<<dq.at(j);
          cout<<'\n';
      }
      i++;
    }
}
string max_arr(string s, int n){

    int i= 0, j = 0, items_in_q=0, start, end;
    int in_q[256] =  {0};
    int maxl = 0;
    while(s[i]!='\0' && s[j]!='\0'){
        if(in_q[s[j]]){
            in_q[s[j]]+=1;
            j++;
        }
        else{
            
            if(items_in_q < n){ 
                items_in_q++; 
                in_q[s[j]]+=1;
                j++;
            }
            else{
                in_q[s[i]]-=1;
                if(in_q[s[i]] == 0){
                    items_in_q--;
                }
                i++;
            }
        }
        
        if((j-i) > maxl)
        {

            string res = s.substr(i, j-i);
            maxl = j-i;
            end = j;
            start = i;
        }
    }
    string res = s.substr(start, end-start);
    return res;
}
int main(){
    string s = "abbabccaaddddddddddddddddaab";
    int n = 4;
    string res = max_arr(s, n);
    max_stream(s, n);
    cout<<res<<'\n';
}
