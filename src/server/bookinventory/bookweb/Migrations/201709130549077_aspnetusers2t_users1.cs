namespace bookweb.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class aspnetusers2t_users1 : DbMigration
    {
        public override void Up()
        {
            RenameTable(name: "dbo.AspNetUsers", newName: "t_users");
        }
        
        public override void Down()
        {
            RenameTable(name: "dbo.t_users", newName: "AspNetUsers");
        }
    }
}
