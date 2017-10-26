using System;
using System.Collections.Generic;
using System.Linq;
using bookweb.beans;
using System.Web;
using bookweb.Utils;
using System.Data;
using System.Data.SqlClient;
using System.Data.SqlTypes;

namespace bookweb.Models
{
    public class BookModel : BaseModel
    {

        public List<Book> getBookList(String title  , String shelfno , String barcode, out int recordCount, int pageIndex = 0 , int pageSize=50 )
        {
            List<Book> books = new List<Book>();

            string where = " 1 = 1 ";
            if (!string.IsNullOrEmpty(title)){
                where += string.Format(" and title like '%{0}%'", title);
            }
            if(!string.IsNullOrEmpty(shelfno))
            {
                where += string.Format(" and shelfno ='{0}'", shelfno);
            }
            if (!string.IsNullOrEmpty(barcode))
            {
                where += string.Format(" and barcode ='{0}'", barcode);
            }

            String sql = "select * from [item_tab] where " + where;
           DataTable dt = GetSplitDataTable(sql, pageSize, pageIndex , "desc" , "updatetime" , out recordCount);
            for( int i = 0; i < dt.Rows.Count; i++)
            {
                Book b = dataRowToBook(dt.Rows[i]);
                books.Add(b);
            }

            return books;
        }

        private Book dataRowToBook(DataRow row)
        {
            Book b = new Book();
            b.barcode = row["barcode"].ToString();
            b.callno = row["callno"].ToString();
            if (row["inshelf"] != null)
            {
                b.inshelf = int.Parse(row["inshelf"].ToString());
            }
            b.machine_mac = row["machine_mac"].ToString();
            b.shelfno = row["shelfno"].ToString();
            b.status = row["status"].ToString();
            b.title = row["title"].ToString();
            b.uid = row["uid"].ToString();
            if (row["updatetime"] != null)
            {
                b.updatetime =DateTime.Parse( row["updatetime"].ToString());
            }else
            {
                b.updatetime = DateTime.Now;
            }
            

            return b;

        }


        public List<Book> getshelflist(String shelfno , out int recordCount , int pageIndex = 0, int pageSize = 200)
        {
            return getBookList("", shelfno, "", out recordCount, pageIndex, pageSize);
        }


        public int UpdateBook(string barcode , string shelfno , DateTime updatetime)
        {
            string sql = "select count(1) from [item_tab] where barcode=@barcode";
            SqlParameter[] parameters = {
                    new SqlParameter("@barcode" , SqlDbType.VarChar,50)
            };
            parameters[0].Value = barcode;

            Object obj =  SqlServerHelper.ExecuteScalar(CommandType.Text, sql, parameters);
            if (obj == null || int.Parse(obj.ToString()) < 1){
                sql = "insert into [item_tab]( barcode , shelfno, updatetime ) values(@barcode,@shelfno , @updatetime)";
                SqlParameter[] parameters2 ={
                    new SqlParameter("@barcode", SqlDbType.VarChar, 50),
                    new SqlParameter("@shelfno",SqlDbType.VarChar,50),
                    new SqlParameter("@updatetime",SqlDbType.DateTime)
                };
                parameters2[0].Value = barcode;
                parameters2[1].Value = shelfno;
                parameters2[2].Value = updatetime;

                int result=SqlServerHelper.ExecuteNonQuery(CommandType.Text, sql, parameters2);
                return result;
            }else
            {
                sql = "update [item_tab] set  shelfno=@shelfno , updatetime =@updatetime where barcode=@barcode ";
                SqlParameter[] parameters2 ={
                    new SqlParameter("@shelfno", SqlDbType.VarChar, 50),
                    new SqlParameter("@updatetime",SqlDbType.DateTime),
                    new SqlParameter("@barcode",SqlDbType.VarChar,50)
                };
                parameters2[0].Value = shelfno;
                parameters2[1].Value = updatetime;
                parameters2[2].Value = barcode;

                int result = SqlServerHelper.ExecuteNonQuery(CommandType.Text, sql, parameters2);
                return result;
            }
        }

    }
}