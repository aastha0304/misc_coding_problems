class Solution {

public:

    int min(int a, int b){

        return a<b?a:b;

    }

    int findMin(vector<int> &a) {

        int n = a.size();

        return fml(a, 0, n-1);

    }

    int fml(vector<int> &a, int l, int h){

        if(l==h)

            return a[l];

        else{

            int mid = l+(h-l)/2;

            int ml = fml(a, l, mid);

            int mr = fml(a, mid+1, h);

            return min(ml, mr);

        }

    }

};
