class Solution {

public:

    int titleToNumber(string s) {

        int i, res = 0;

        for(i=0;s[i]!='\0';i++){

            int a = s[i]-64;

            res = res*26+a;

        }

        return res;

    }

};
