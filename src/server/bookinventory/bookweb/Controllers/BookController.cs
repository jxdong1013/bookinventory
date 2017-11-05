using System;
using System.Collections.Generic;
using System.Web.Mvc;
using bookweb.beans;
using bookweb.Utils;
using bookweb.Models;
using System.Collections;

namespace bookweb.Controllers
{
    //[HandlerLoginAttribute]
    public class BookController : Controller
    {
        [HttpGet]
        public JsonResult GetBookList(string title  , string barcode , string shelfno , int pageidx = 1 , int pagesize = 20 )
        {
            MyJsonResult result = new MyJsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;

            BookModel bookModel = new BookModel();


            int totalcount = 0;
            List<Book> books = bookModel.getBookList(title, barcode, shelfno, out totalcount , pageidx, pagesize);
          

            Page<Book> page = new Page<Book>();
            page.data = books;
            page.pageIdx = pageidx;
            page.pageCount = totalcount/pagesize + totalcount%pagesize>0? 1 : 0 ;
            page.pageSize = pagesize;
            page.totalCount = totalcount;

            DataBase<Page<Book>> data = new DataBase<Page<Book>>();
            data.code = Config.success_code;
            data.message = "success";
            data.data = page;

            result.Data = data;
            
                      

            return result;
        }

        [HttpGet]
        public JsonResult GetShelfList(string shelfno)
        {
            MyJsonResult result = new MyJsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;

            DataBase<Shelf> data = new DataBase<Shelf>();
                       

            if (string.IsNullOrEmpty(shelfno))
            {
                data.code = Config.error_code;
                data.message="缺少架位标签编号参数";
                result.Data = data;
                return result;
            }

            BookModel bookModel = new BookModel();
            int totalcount = 0;
            List<Book> books = bookModel.getshelflist(shelfno, out totalcount, 1, 500);
            Shelf shelf = new Shelf();
            shelf.books = books;
            shelf.shelfno = shelfno;

            data.code = Config.success_code;
            data.message = "success";
            data.data = shelf;
            result.Data = data;
            return result;
        }


        [HttpPost]
        public JsonResult uploadShelf(List<Book> books )
        {
            MyJsonResult result = new MyJsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            DataBase<List<KV>> data = new DataBase<List<KV>>();
            if (books == null || books.Count < 1)
            {             
                data.code = Config.error_code;
                data.message = "请提供盘点数据";
                result.Data = data;
                return result;
            }

            books.Sort();
            List<KV> kv = new List<KV>();
            BookModel bookModel = new BookModel();
            foreach(Book b in books)
            {
                if( string.IsNullOrEmpty( b.barcode) || string.IsNullOrEmpty(b.shelfno))
                {
                    //hs.Add(b.barcode, 0);
                    continue;
                }
                int code =bookModel.UpdateBook(b.barcode, b.shelfno, b.updatetime);
                KV k = new KV();
                k.key = b.barcode;
                k.value = code.ToString();
                kv.Add(k);
            }

            data.code = Config.success_code;
            data.data = kv;
            result.Data = data;
            return result;
        }

    }
}
