class Solution {

public:

    vector<vector<int> > generate(int numRows) {

        vector<vector<int> > res;

        if(numRows==0){

            //vector<int> v(0);

            //v.push_back(1);

            //for(int i=0;i<v.size();i++)

              //  cout<<v[i];

            

            //res.push_back(v);

            return res;

        }else{

            vector<int> v;

            v.push_back(1);

            res.push_back(v);

            //print_so_far(res, 1);

            for(int i=1;i<numRows;i++){

                vector<int> v;

                v.push_back(1);



                for(int j=1;j<i;j++){

                    //print_so_far(res, i);

                    v.push_back(res.at(i-1).at(j-1)+res.at(i-1).at(j));

                }

                v.push_back(1);

                res.push_back(v);

                //print_so_far(res, i);

            }

            return res;

        }

    }

};
