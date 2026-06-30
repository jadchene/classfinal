package net.roseboy.classfinal.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JarUtilsTest {

    @Test
    public void shouldResolvePlainJarPath() {
        assertEquals("/opt/apps/demo.jar", JarUtils.getRootPath("/opt/apps/demo.jar"));
    }

    @Test
    public void shouldResolveOldSpringBootJarUrl() {
        String path = "jar:file:/opt/apps/demo.jar!/BOOT-INF/classes!/net/roseboy/classfinal/util/";

        assertEquals("/opt/apps/demo.jar", JarUtils.getRootPath(path));
    }

    @Test
    public void shouldResolveSpringBoot3NestedClassesUrl() {
        String path = "jar:nested:/opt/apps/demo.jar/!BOOT-INF/classes/!/net/roseboy/classfinal/util/";

        assertEquals("/opt/apps/demo.jar", JarUtils.getRootPath(path));
    }

    @Test
    public void shouldResolveSpringBoot3NestedLibUrlToOuterJar() {
        String path = "nested:/opt/apps/demo.jar/!BOOT-INF/lib/service.jar!/com/example/";

        assertEquals("/opt/apps/demo.jar", JarUtils.getRootPath(path));
    }

    @Test
    public void shouldResolveWindowsSpringBoot3NestedUrl() {
        String path = "jar:nested:/E:/apps/demo.jar/!BOOT-INF/classes/!/net/roseboy/classfinal/util/";

        assertEquals("/E:/apps/demo.jar", JarUtils.getRootPath(path));
    }

    @Test
    public void shouldResolveExplodedWarPath() {
        String path = "/opt/tomcat/webapps/demo/WEB-INF/classes/net/roseboy/classfinal/util/";

        assertEquals("/opt/tomcat/webapps/demo/", JarUtils.getRootPath(path));
    }

    @Test
    public void shouldReturnNullForUnknownPath() {
        assertNull(JarUtils.getRootPath("/opt/apps/classes-like-directory/net/roseboy/"));
    }
}
