package test;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;



public class MyTest {
    private void dumpStream(Resource resource) {
        InputStream is = null;
        try {
            //1.获取文件资源
            is = resource.getInputStream();
            //2.读取资源
            byte[] descBytes = new byte[is.available()]; 
            is.read(descBytes);
            System.out.println(new String(descBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                //3.关闭资源
                is.close();
            } catch (IOException e) {
            }
        }
    }
   @Test
   public void test1() throws IOException{
       Class clazz = this.getClass();
       ClassLoader cl = this.getClass().getClassLoader();
       Resource resource1 = 
               new ClassPathResource("test1.properties",cl );
       dumpStream(resource1);
      System.out.println(resource1.getFile().getAbsolutePath());
   }
   @Test
   public void testResourceLoad() throws IOException {
       ResourceLoader loader = new DefaultResourceLoader();
       Resource resource = loader.getResource("classpath:test1.properties");
       dumpStream(resource) ;
  // 验证返回的是ClassPathResource
   Assert.assertEquals(ClassPathResource.class, resource.getClass());
   Resource resource2 = loader.getResource("file:cn/javass/spring/chapter4/test1.txt");
       //验证返回的是ClassPathResource
   System.out.println(resource2.getURL());
   Assert.assertEquals(UrlResource.class, resource2.getClass());
       Resource resource3 = loader.getResource("cn/javass/spring/chapter4/test1.txt");
       //验证返默认可以加载ClasspathResource
       Assert.assertTrue(resource3 instanceof ClassPathResource);
   }
   @Test
   public void test() {
   ApplicationContext ctx = 
   new ClassPathXmlApplicationContext("test1.properties");
  
    }
   @Test
   public void testClasspathPrefix() throws IOException {
       ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
       
       //只加载一个绝对匹配Resource，且通过ResourceLoader.getResource进行加载
       Resource[] resources = resolver.getResources("classpath:META-INF/INDEX.LIST");
       System.out.println( resources.length);

       //只加载一个匹配的Resource，且通过ResourceLoader.getResource进行加载
       resources = resolver.getResources("classpath:META-INF/INDEX.LIST");
       System.out.println( resources.length);
       

       //只加载一个绝对匹配Resource，且通过ResourceLoader.getResource进行加载
    //   resources = resolver.getResources("classpath:META-INF/MANIFEST.MF");
    //   Assert.assertEquals(1, resources.length);
   }
}
