import { myAxios } from "./helper";

export const signUp=(user)=>{
    return myAxios.post('/v1/api/auth/register', user).then((response)=>response.data)
}