using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _12306
{
    class Program
    {
        static string _root;
        static string[] _updates;
        static string _pluginVersion;
        static string OriginalScript = "12306\\12306_ticket_helper.user.js";
        static string manifest_json =  "12306\\manifest.json";

        static void Main(string[] args)
        {
            _root = GetContainingFolder();

            Console.WriteLine("[INFO] 项目根目录是 " + _root);

            //获得插件版本
            _pluginVersion = GetVersion();

            Console.WriteLine("插件版本:" + _pluginVersion);
            UpdateManifestQwop( System.IO.Path.Combine(_root, manifest_json) );
         

            Console.WriteLine("====================================================");
            Console.WriteLine("按任意键退出.....");
            Console.ReadKey();

        }

        static string GetOriginalScript()
        {
            return System.IO.Path.Combine(_root, OriginalScript);
        }


        /// <summary>
        /// 查找当前项目的根目录
        /// </summary>
        /// <returns></returns>
        static string GetContainingFolder()
        {
            var root = System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);
            if (System.IO.Directory.Exists(System.IO.Path.Combine(root, ".git"))) return root;

            //当前没有.git目录，尝试查找项目根目录
            var tempRoot = root;
            var lastIndex = 0;
            while ((lastIndex = tempRoot.LastIndexOf("\\")) != -1)
            {
                tempRoot = tempRoot.Substring(0, lastIndex);
                if (System.IO.Directory.Exists(System.IO.Path.Combine(tempRoot, ".git"))) return tempRoot;
            }

            return root;
        }


        /// <summary>
        /// 读取助手的版本，并更新助手信息中的版本号
        /// </summary>
        /// <returns></returns>
        static string GetVersion()
        {
            Console.WriteLine("[INFO] 正在获得助手版本号……");
             var txt = System.IO.File.ReadAllLines(GetOriginalScript(), System.Text.Encoding.UTF8);
             var allTxt = System.IO.File.ReadAllText(GetOriginalScript(), System.Text.Encoding.UTF8);

            System.Text.RegularExpressions.Match match = null;
            var versionReg = new System.Text.RegularExpressions.Regex(@"var\s+version\s*=\s*['""]([\.\d]+)['""]");
            txt.FirstOrDefault(s => (match = versionReg.Match(s)).Success);
            if (match == null)
            {
                throw new Exception("[ERROR] 无法找到版本标记");
            }

            var version = match.Groups[1].Value;
            

            
           //  ModifyManifestVersion(System.IO.Path.Combine(_root, manifest_json ), version);

           //  updates = ["+ 自动刷新按钮增加刷学生票", "* 更新兼容性代码", "* 查票逻辑更改，在普通查询下不再有声音和提示，在自动刷票下才有提示和声音", "* 修正当不能进行查询的时候，点击自动刷票后按钮显示不对的BUG", "* 修正按回车选择日期和站点的时候会导致开始查询的BUG", "* 修正点击自动刷票按钮以及在刷票过程中修改条件时，查询的条件无法保存的BUG", "* 高级工具区的显示或隐藏设置可以记录", "* 界面细节调整，简化文字使用量，变更部分文字说明，尝试简化界面"],
            //查找更新

          
            
          /*  System.Text.RegularExpressions.Match m2 = null;
          
           var updatesReg = new System.Text.RegularExpressions.Regex(@"updates\s*=\s*\[(.+)""\]\,faqUrl");
            txt.FirstOrDefault(s => (m2 = updatesReg.Match(s)).Success);
   
            if (m2 == null)
            {
                throw new Exception("[ERROR] 无法找到版本标记");
            }

            var updates = m2.Groups[1].Value;
            Console.WriteLine("updates:" + updates);

            */
          /*  System.Text.RegularExpressions.Regex.Replace(
                txt,
                @"[""']version[""']\s*:\s*['""][\.\d+]+['""]",
                "\"version\": \"" + version + "\""
                 ); */
            return version;
        }

        /// <summary>
        /// 修改指定清单的版本
        /// </summary>
        /// <param name="filePath"></param>
        /// <param name="version"></param>
        public static void UpdateManifestQwop(string filePath)
        {
            /*
             [ "12306_ticket_helper.user.js" ]
             [ "12306_ticket_helper.user.js" , "tan.js" ]
             "js": [ "12306_ticket_helper.user.js" ],
             */
            var qwop_js = "[ \"12306_ticket_helper.user.js\" , \"tan.js\" ]";
            var manifest = System.IO.File.ReadAllText(filePath, System.Text.Encoding.UTF8);
            var src = System.IO.Path.Combine(_root, "tan.js");
            var dest = System.IO.Path.Combine(_root, "12306\\tan.js");

            // js files.
            manifest = System.Text.RegularExpressions.Regex.Replace(
                manifest,
                @"[""']js[""']\s*:\s*\[\s*(['""]12306_ticket_helper.user.js['""])\s*\]",

                "\"js\": " + qwop_js + ""
                 );

           // default title
           manifest = System.Text.RegularExpressions.Regex.Replace(
                manifest,
                @"[""']default_title[""']\s*:\s*['""](.+)['""]",
                "\"default_title\": \"$1 modify by qwop\""
            );


           // description
           manifest = System.Text.RegularExpressions.Regex.Replace(
                manifest,
                @"[""']description[""']\s*:\s*['""](.+)['""]",
                "\"description\": \"$1 modify by qwop\""
            );

            System.IO.File.WriteAllText(filePath, manifest, System.Text.Encoding.UTF8);
            if (System.IO.File.Exists(src)) {
                Console.WriteLine("开始复制文件:tan.js");
                System.IO.File.Copy(src, dest, true );
            }
            
        }


        /// <summary>
        /// 修改指定清单的版本
        /// </summary>
        /// <param name="filePath"></param>
        /// <param name="version"></param>
        public static void ModifyManifestVersion(string filePath, string version)
        {
            ModifyManifestVersion(filePath, version, System.Text.Encoding.UTF8);
        }


        /// <summary>
        /// 修改指定清单的版本
        /// </summary>
        /// <param name="filePath"></param>
        /// <param name="version"></param>
        public static void ModifyManifestVersion(string filePath, string version, System.Text.Encoding encoding)
        {
            var manifest = System.IO.File.ReadAllText(filePath, encoding);
            manifest = System.Text.RegularExpressions.Regex.Replace(
                manifest,
                @"[""']version[""']\s*:\s*['""][\.\d+]+['""]",
                "\"version\": \"" + version + "\""
                 );
            System.IO.File.WriteAllText(filePath, manifest, encoding);
        }
    }



}
