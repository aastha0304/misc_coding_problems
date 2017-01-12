#include <iostream>
#include <vector>
#include <deque>
using namespace std;
class Solution {
public:
    int trailingZeroes(int n) {
        int count = 0;
        int i,j;
        // Keep dividing n by powers of 5 and update count
        for (i=5, j=1; n/i>=1; i *= 5,  j++){
          count += n/i;
          if(n/i==1)
            break;
        }
        return count;
    }
};
int main(){
   unsigned int n = 1808548329;
   cout<<n;
   Solution s;
   cout<<s.trailingZeroes(n);
}