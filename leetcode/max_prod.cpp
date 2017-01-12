class Solution {

public:

    int maxProduct(int A[], int n) {

        int *left=new int[n];

        int *right = new int[n];

        left[0] = A[0];

        right[n-1] = A[n-1];

        int i;

        for(i=1;i<n;i++)

        {

            if(left[i-1]!=0)

                left[i] = left[i-1]*A[i];

            else

                left[i] = A[i];

        }

        for(i=n-2;i>=0;i--)

        {

            if(right[i+1]!=0)

                right[i] = right[i+1]*A[i];

            else

                right[i] = A[i];

        }

        int mx=left[0];

        for(i=0;i<n;i++)

            if(mx<left[i])

                mx = left[i];

        for(i=0;i<n;i++)

            if(mx<right[i])

                mx = right[i];

        return mx;

    }

};
