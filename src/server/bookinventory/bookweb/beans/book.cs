using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class Book :IComparable<Book>
    {
        public string title { get; set; }
        //public string author { get; set; }
        //public string publish { get; set; }
        //public string publishDate { get; set; }
        //public string bookId { get; set; }
        public string barcode { get; set; }

        public string uid { get; set; }

        public string callno { get; set; }

        public string shelfno { get; set; }

        //public string position { get; set; }

        public string status { get; set; }

        public DateTime updatetime { get; set; }

        public int inshelf { get; set; }

        public string machine_mac { get; set; }


        public int CompareTo(Book other)
        {
            if (other==null) return 1;
            return this.updatetime.CompareTo(other.updatetime);
        }
    }
}