class Solution {

public:

    int findMin(vector<int> &num) {

        int n = num.size();

        int l = 0, h = n-1;

        while(l<h){

            int mid = l+(h-l)/2;

            if(mid>0 && num[mid-1]>num[mid])

                return num[mid];

            else if(num[mid] > num[mid+1])

                return num[mid+1];

            else if(num[l]<num[mid]){

                if(num[mid]<num[h])

                    return num[l];

                else

                    l = mid + 1;

            }else{

                h = mid - 1;

            }

        }

        return num[l];

    }

};
