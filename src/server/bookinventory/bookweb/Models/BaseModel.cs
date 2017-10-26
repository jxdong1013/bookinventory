using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlTypes;
using System.Data.SqlClient;
using bookweb.Utils;
using System.Collections;

namespace bookweb.Models
{
    public class BaseModel
    {
        /// <summary>
        /// 获取分页DataTable 【 2012-08-23】
        /// </summary>
        /// <param name="strSql"></param>
        /// <param name="iPageSize">分页大小</param>
        /// <param name="iPageIndex">当前页</param>
        /// <param name="iRecordCount">总记录数</param>
        /// <returns></returns>
        public static DataTable GetSplitDataTable(string strSql, int iPageSize, int iPageIndex, string sort , string sortname , out int iRecordCount)
        {
            SqlParameter[] parameters = {
                                            new SqlParameter("@sqlString", SqlDbType.Text),
                                            new SqlParameter("@pageIndex", SqlDbType.Int, 4),
                                            new SqlParameter("@pageSize", SqlDbType.Int, 4),                                            
                                            new SqlParameter("@sort", SqlDbType.VarChar),
                                            new SqlParameter("@keyname",SqlDbType.VarChar),
                                            new SqlParameter("@pageTotal",SqlDbType.Int,4)
                                        };
            parameters[0].Value = strSql;
            parameters[1].Value = iPageIndex;
            parameters[2].Value = iPageSize;
            parameters[3].Value = sort;
            parameters[4].Value = sortname;
            parameters[5].Direction = ParameterDirection.Output;

            DataTable dt = RunDoSplitPage(parameters,  out iRecordCount);
            return dt;
        }

        /// <summary>
        /// 执行分页存储过程【fulh 2011-11-20】
        /// </summary>
        /// <param name="parameters"></param>
        /// <param name="recordCount"></param>
        /// <returns></returns>
        private static DataTable RunDoSplitPage(SqlParameter[] parameters, out int recordCount)
        {
            //using (SqlConnection connection = new SqlConnection(connectionString))
            //{
            //    DataSet dataSet = new DataSet();
            //    connection.Open();

            //    SqlDataAdapter sqlDA = new SqlDataAdapter();
            //    sqlDA.SelectCommand = BuildQueryCommand(connection, "DoSplitPage", parameters);
            //    sqlDA.Fill(dataSet);

            //    recordCount = Convert.ToInt32(sqlDA.SelectCommand.Parameters["@RecordCount"].Value);

            //    connection.Close();

            //    DataTable dt = dataSet.Tables[1];
            //    if (dt.Columns.Contains("ROWSTAT"))//游标多出的一列
            //        dt.Columns.Remove("ROWSTAT");
            //    return dt;
            //}

            Hashtable hsOutParameters = new Hashtable();
            
            DataSet dataSet = SqlServerHelper.ExecuteDataSet(ref hsOutParameters, "sp_DoSplitPage", CommandType.StoredProcedure, parameters);
            DataTable dt = dataSet.Tables[0];

            recordCount = int.Parse( hsOutParameters["@pageTotal"].ToString());
          

            if (dt.Columns.Contains("ROWSTAT"))
            {//游标多出的一列
                dt.Columns.Remove("ROWSTAT");
            }
            return dt;
        
        }

    }
}