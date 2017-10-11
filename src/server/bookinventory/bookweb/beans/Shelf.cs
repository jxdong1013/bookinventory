using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class Shelf
    {
        public string shelfCode { get; set; }
        public string shelfName { get; set; }
        public List<Book> books { get; set; }
    }
}