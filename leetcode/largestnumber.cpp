#include <iostream>
#include <vector>
#include <iterator>
#include <string>
#include <algorithm>
#include <sstream>
using namespace std;
template <typename T>
string to_string(T value)
{
  //create an output string stream
  std::ostringstream os ;

  //throw the value into the string stream
  os << value ;

  //convert the string stream into a string and return
  string res = os.str() ;
  return res;
}
class Solution {
public:
    static bool comp(string a, string b){
        string s = a+b;
        string t = b+a;
        if(s>t)
            return true;
        else
            return false;
    }
    void print(vector<string> &a){
        int s = a.size();
        for(size_t i=0;i<s;i++)
            cout<<a[i]<<' ';
        cout<<endl;
    }
    string largestNumber(vector<int> &num) {
        int s = num.size();
        vector<string> a;
        for(int i=0;i<s;i++){
            string b = to_string(num[i]);
            a.push_back(b);
        }
        sort(a.begin(), a.end(), &comp);
        //print(a);
        if(s>0 && a[0]=="0")
            return "0";
        string str = "";
        for(size_t i=0;i<s;i++)
            str+=a[i];
        return str;
    }
};

int main(){
    int a[] = {3, 30, 34, 5, 9};
    int l = sizeof(a)/sizeof(*a);
    vector<int> v(a, a + l);
    Solution s;
    string res = s.largestNumber(v);
    cout<<res<<endl;
    return 0;
}