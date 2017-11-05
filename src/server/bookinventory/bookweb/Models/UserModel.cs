using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using bookweb.beans;
using System.Data.SqlClient;
using System.Data;
using bookweb.Utils;


namespace bookweb.Models
{
    public class UserModel 
    {
        public User  login(string username , string password)
        {
            string sql = "select * from [user] where username=@username and password=@password";
          
            SqlParameter[] parameters ={
                    new SqlParameter("@username", SqlDbType.VarChar,50),
                    new SqlParameter("@password", SqlDbType.VarChar,50)
                                         };
            parameters[0].Value = username;
            parameters[1].Value = password;

            DataSet result = SqlServerHelper.ExecuteDataSet (sql , CommandType.Text , parameters);
            if (result == null || result.Tables.Count < 1 || result.Tables[0].Rows.Count < 1) return null;
            User model = new User();
            if (result.Tables[0].Rows[0]["userid"].ToString() != "")
            {
                model.userid = int.Parse(result.Tables[0].Rows[0]["userid"].ToString());
            }
            model.username = result.Tables[0].Rows[0]["username"].ToString();
            //model.password = result.Tables[0].Rows[0]["password"].ToString();
            DateTime temp;
            if (DateTime.TryParse(result.Tables[0].Rows[0]["createtime"].ToString(), out temp))
            {
                model.createtime = temp;
            }
            else
            {
                model.createtime = DateTime.Now;
            }         
            model.phone = result.Tables[0].Rows[0]["phone"].ToString();
            model.type = result.Tables[0].Rows[0]["type"].ToString();
            model.realname = result.Tables[0].Rows[0]["realname"].ToString();

            return model;
        }


        public bool register(User model)
        {
            string sql = string.Format(" insert into [user] ( username , password ,  phone , type, createtime,realname) values(@username,@password,@phone,@type,@createtime,@realname)");
            SqlParameter[] paras = new SqlParameter[6];
            paras[0] = new SqlParameter();
            paras[0].ParameterName = "@username";
            paras[0].SqlDbType = SqlDbType.VarChar;
            paras[0].Value = model.username;
            paras[1] = new SqlParameter();
            paras[1].ParameterName = "@password";
            paras[1].SqlDbType = SqlDbType.VarChar;
            paras[1].Value = model.password;
            paras[2] = new SqlParameter();
            paras[2].ParameterName = "@phone";
            paras[2].SqlDbType = SqlDbType.VarChar;
            paras[2].Value = model.phone;
            paras[3] = new SqlParameter();
            paras[3].ParameterName = "@type";
            paras[3].SqlDbType = SqlDbType.VarChar;
            paras[3].Value = model.type;
            paras[4] = new SqlParameter();
            paras[4].ParameterName = "@createtime";
            paras[4].SqlDbType = SqlDbType.DateTime;
            paras[4].Value = model.createtime;
            paras[5] = new SqlParameter();
            paras[5].ParameterName = "@realname";
            paras[5].SqlDbType = SqlDbType.VarChar;
            paras[5].Value = model.realname;

            int result = SqlServerHelper.ExecuteNonQuery(sql, paras);
            return result>0;

        }

    }
}