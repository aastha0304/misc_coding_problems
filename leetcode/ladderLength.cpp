class Solution {

public:

    int ladderLength(string start, string end, unordered_set<string> &dict) {

        deque <string> ls;

        map<string,bool> visited;

        if(start.compare(end)==0)

            return 1;

        char arr[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        int ln = start.length();

        ls.push_back(start);

        ls.push_back("|");

        visited[start] = true;

        int l = 0;

        while(!ls.empty()){

            string st = ls.front();

            ls.pop_front();

            //cout <<st<<'\n';

            if(st.compare("|")==0){

                

                if(!ls.empty()){

                    ls.push_back("|");

                    l += 1;

                    //cout<<l<<'\n';

                }

                else{

                    //cout<<l<<'\n';

                    return l==0?0:l+1;

                }

            }

            else{

                int i = 0;

                while(i < ln){

                    string str = st;

                    for(int j = 0;j<26;j++){

                        str[i] = arr[j];

                        //cout <<str<<'\n';

                        if(str.compare(end)==0){

                            //cout<<l<<'\n';

                            return l+2;}

                        else if(dict.find(str) != dict.end()){

                          //cout <<str<<" in set"<<'\n';

                          if(visited.find(str) == visited.end()){

                            //cout <<str<<" not in visited"<<'\n';

                            visited[str] = true;

                            ls.push_back(str);

                          }

                        }

                    }

                    i += 1;

                }

                //return 0;

            }

        }

        return 0;

    }

};
