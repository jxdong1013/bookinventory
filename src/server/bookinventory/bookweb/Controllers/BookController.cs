using System;
using System.Collections.Generic;
using System.Web.Mvc;
using bookweb.beans;
using bookweb.Utils;

namespace bookweb.Controllers
{
    [HandlerLoginAttribute]
    public class BookController : Controller
    {
        [HttpGet]
        public JsonResult GetBookList(string key , string code , int pageidx = 0 , int pagesize = 20 )
        {
            JsonResult result = new JsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;

         
                List<Book> books = new List<Book>();
            if (pageidx < 8)
            {
                for (int i = 0; i < pagesize; i++)
                {
                    Book b = new Book();
                    b.author = "金向东";
                    b.bookcode = Guid.NewGuid().ToString();
                    b.bookId = (pageidx * pagesize + i).ToString();
                    b.bookName = Guid.NewGuid().ToString();
                    b.publish = "浙江杭州人民出版社";
                    b.publishDate = DateTime.Now.ToString("yyyy-MM-dd");
                    b.position = "100架2排";
                    books.Add(b);
                }
            }

            Page<Book> page = new Page<Book>();
            page.data = books;
            page.pageIdx = pageidx;
            page.pageCount = 0;
            page.pageSize = pagesize;
            page.totalCount = 0;

            DataBase<Page<Book>> data = new DataBase<Page<Book>>();
            data.code = Config.success_code;
            data.message = "success";
            data.data = page;

            result.Data = data;
            return result;
        }

        // GET: api/Book/5
        public string Get(int id)
        {
            return "value";
        }


        // DELETE: api/Book/5
        public void Delete(int id)
        {
        }
    }
}
