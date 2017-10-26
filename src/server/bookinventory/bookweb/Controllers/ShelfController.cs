using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using bookweb.beans;
using System.Web.Mvc;
using bookweb.Utils;

namespace bookweb.Controllers
{
    [HandlerLoginAttribute]
    public class ShelfController : Controller
    {
        // GET: Shelf
        public ActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public JsonResult GetShelfList(string code  )
        {
            JsonResult result = new JsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;

            Shelf shelf = new Shelf();
            //shelf.shelfCode = "234233432";
            //shelf.shelfName = "架子"+Guid.NewGuid().ToString();
            List<Book> books = new List<Book>();
            for(int i = 0; i < 50; i++)
            {
                Book b = new Book();
                //b.author = "金向东";
                //b.bookcode = Guid.NewGuid().ToString();
                //b.bookId = i.ToString();
                //b.bookName="产生的是否撒";
                //b.position = "2层2架3排";
                //b.publish = "浙江省杭州市人民出版社";
                //b.publishDate = "2017-01-11";
                b.status = i %2 == 0 ?  Config.book_status_in : Config.book_status_out;
                books.Add(b);
            }
            shelf.books = books;

            DataBase<Shelf> data = new DataBase<Shelf>();
            data.code = Config.success_code;
            data.message = "success";
            data.data = shelf;

            result.Data = data;

            return result;

        }

        [HttpPost]
        public JsonResult UploadShelfList(List<BookShelfAdapt> data) 
        {
            JsonResult json = new JsonResult();
            json.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            return json;
        }
    }
}