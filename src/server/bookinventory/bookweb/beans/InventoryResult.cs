using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class InventoryResult
    {
        public int index { get; set; }
        public List<Inventory> data { get; set; }
    }
}