using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class Page<T>
    {
        public int pageIdx { get; set; }
        public int totalCount { get; set; }
        public List<T> data { get; set; }
        public int pageCount { get; set; }
        public int pageSize { get; set; }
    }
}