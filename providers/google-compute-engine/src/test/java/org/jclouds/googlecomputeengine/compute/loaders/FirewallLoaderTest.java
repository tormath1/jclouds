package org.jclouds.googlecomputeengine.compute.loaders;

import org.jclouds.googlecomputeengine.compute.functions.Resources;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.concurrent.ExecutionException;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.testng.AssertJUnit.assertFalse;

@Test(groups = "unit", testName = "FirewallLoaderTest")
public class FirewallLoaderTest {
    private static final URI firewall_uri = URI.create("https://foo/bar/firewall/1");

    public void testFirewallNotFound() throws ExecutionException {
        Resources resources = mock(Resources.class);
        expect(resources.firewall(firewall_uri)).andReturn(null);
        replay(resources);

        FirewallLoader firewallLoader = new FirewallLoader(resources);
        assertFalse(firewallLoader.load(firewall_uri).isPresent());
    }
}
