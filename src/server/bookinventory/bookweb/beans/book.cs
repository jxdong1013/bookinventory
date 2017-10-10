using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class Book
    {
        public string bookName { get; set; }
        public string author { get; set; }
        public string publish { get; set; }
        public string publishDate { get; set; }
        public string bookId { get; set; }
        public string bookcode { get; set; }
    }
}