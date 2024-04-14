package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `versions` extension.
 */
@NonNullApi
public class LibrariesForVersionsInPluginsBlock extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForVersionsInPluginsBlock(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public BundleAccessors getBundles() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class VersionAccessors extends VersionFactory  {

        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final CoilVersionAccessors vaccForCoilVersionAccessors = new CoilVersionAccessors(providers, config);
        private final ComposeVersionAccessors vaccForComposeVersionAccessors = new ComposeVersionAccessors(providers, config);
        private final DaggerVersionAccessors vaccForDaggerVersionAccessors = new DaggerVersionAccessors(providers, config);
        private final EspressoVersionAccessors vaccForEspressoVersionAccessors = new EspressoVersionAccessors(providers, config);
        private final GradleVersionAccessors vaccForGradleVersionAccessors = new GradleVersionAccessors(providers, config);
        private final JunitVersionAccessors vaccForJunitVersionAccessors = new JunitVersionAccessors(providers, config);
        private final MockkVersionAccessors vaccForMockkVersionAccessors = new MockkVersionAccessors(providers, config);
        private final OkhttpVersionAccessors vaccForOkhttpVersionAccessors = new OkhttpVersionAccessors(providers, config);
        private final RetrofitVersionAccessors vaccForRetrofitVersionAccessors = new RetrofitVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.androidx
         */
        public AndroidxVersionAccessors getAndroidx() {
            return vaccForAndroidxVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.coil
         */
        public CoilVersionAccessors getCoil() {
            return vaccForCoilVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.compose
         */
        public ComposeVersionAccessors getCompose() {
            return vaccForComposeVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.dagger
         */
        public DaggerVersionAccessors getDagger() {
            return vaccForDaggerVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.espresso
         */
        public EspressoVersionAccessors getEspresso() {
            return vaccForEspressoVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.gradle
         */
        public GradleVersionAccessors getGradle() {
            return vaccForGradleVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.junit
         */
        public JunitVersionAccessors getJunit() {
            return vaccForJunitVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.mockk
         */
        public MockkVersionAccessors getMockk() {
            return vaccForMockkVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.okhttp
         */
        public OkhttpVersionAccessors getOkhttp() {
            return vaccForOkhttpVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.retrofit
         */
        public RetrofitVersionAccessors getRetrofit() {
            return vaccForRetrofitVersionAccessors;
        }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        private final AndroidxActivityVersionAccessors vaccForAndroidxActivityVersionAccessors = new AndroidxActivityVersionAccessors(providers, config);
        private final AndroidxCoreVersionAccessors vaccForAndroidxCoreVersionAccessors = new AndroidxCoreVersionAccessors(providers, config);
        private final AndroidxHiltVersionAccessors vaccForAndroidxHiltVersionAccessors = new AndroidxHiltVersionAccessors(providers, config);
        private final AndroidxLifecycleVersionAccessors vaccForAndroidxLifecycleVersionAccessors = new AndroidxLifecycleVersionAccessors(providers, config);
        private final AndroidxMultidexVersionAccessors vaccForAndroidxMultidexVersionAccessors = new AndroidxMultidexVersionAccessors(providers, config);
        private final AndroidxNavigationVersionAccessors vaccForAndroidxNavigationVersionAccessors = new AndroidxNavigationVersionAccessors(providers, config);
        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.androidx.activity
         */
        public AndroidxActivityVersionAccessors getActivity() {
            return vaccForAndroidxActivityVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx.core
         */
        public AndroidxCoreVersionAccessors getCore() {
            return vaccForAndroidxCoreVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx.hilt
         */
        public AndroidxHiltVersionAccessors getHilt() {
            return vaccForAndroidxHiltVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx.lifecycle
         */
        public AndroidxLifecycleVersionAccessors getLifecycle() {
            return vaccForAndroidxLifecycleVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx.multidex
         */
        public AndroidxMultidexVersionAccessors getMultidex() {
            return vaccForAndroidxMultidexVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx.navigation
         */
        public AndroidxNavigationVersionAccessors getNavigation() {
            return vaccForAndroidxNavigationVersionAccessors;
        }

    }

    public static class AndroidxActivityVersionAccessors extends VersionFactory  {

        private final AndroidxActivityComposeVersionAccessors vaccForAndroidxActivityComposeVersionAccessors = new AndroidxActivityComposeVersionAccessors(providers, config);
        public AndroidxActivityVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.androidx.activity.compose
         */
        public AndroidxActivityComposeVersionAccessors getCompose() {
            return vaccForAndroidxActivityComposeVersionAccessors;
        }

    }

    public static class AndroidxActivityComposeVersionAccessors extends VersionFactory  {

        public AndroidxActivityComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.activity.compose.version (1.8.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("androidx.activity.compose.version"); }

    }

    public static class AndroidxCoreVersionAccessors extends VersionFactory  {

        public AndroidxCoreVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.core.version (1.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("androidx.core.version"); }

    }

    public static class AndroidxHiltVersionAccessors extends VersionFactory  {

        private final AndroidxHiltNavigationVersionAccessors vaccForAndroidxHiltNavigationVersionAccessors = new AndroidxHiltNavigationVersionAccessors(providers, config);
        public AndroidxHiltVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.androidx.hilt.navigation
         */
        public AndroidxHiltNavigationVersionAccessors getNavigation() {
            return vaccForAndroidxHiltNavigationVersionAccessors;
        }

    }

    public static class AndroidxHiltNavigationVersionAccessors extends VersionFactory  {

        public AndroidxHiltNavigationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.hilt.navigation.version (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("androidx.hilt.navigation.version"); }

    }

    public static class AndroidxLifecycleVersionAccessors extends VersionFactory  {

        public AndroidxLifecycleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.lifecycle.version (2.7.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("androidx.lifecycle.version"); }

    }

    public static class AndroidxMultidexVersionAccessors extends VersionFactory  {

        public AndroidxMultidexVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.multidex.version (2.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("androidx.multidex.version"); }

    }

    public static class AndroidxNavigationVersionAccessors extends VersionFactory  {

        public AndroidxNavigationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.navigation.version (2.7.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("androidx.navigation.version"); }

    }

    public static class CoilVersionAccessors extends VersionFactory  {

        private final CoilComposeVersionAccessors vaccForCoilComposeVersionAccessors = new CoilComposeVersionAccessors(providers, config);
        public CoilVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.coil.compose
         */
        public CoilComposeVersionAccessors getCompose() {
            return vaccForCoilComposeVersionAccessors;
        }

    }

    public static class CoilComposeVersionAccessors extends VersionFactory  {

        public CoilComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: coil.compose.version (1.3.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("coil.compose.version"); }

    }

    public static class ComposeVersionAccessors extends VersionFactory  {

        public ComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: compose.version (2023.08.00)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("compose.version"); }

    }

    public static class DaggerVersionAccessors extends VersionFactory  {

        private final DaggerHiltVersionAccessors vaccForDaggerHiltVersionAccessors = new DaggerHiltVersionAccessors(providers, config);
        public DaggerVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.dagger.hilt
         */
        public DaggerHiltVersionAccessors getHilt() {
            return vaccForDaggerHiltVersionAccessors;
        }

    }

    public static class DaggerHiltVersionAccessors extends VersionFactory  {

        public DaggerHiltVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: dagger.hilt.version (2.48.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("dagger.hilt.version"); }

    }

    public static class EspressoVersionAccessors extends VersionFactory  {

        private final EspressoCoreVersionAccessors vaccForEspressoCoreVersionAccessors = new EspressoCoreVersionAccessors(providers, config);
        public EspressoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.espresso.core
         */
        public EspressoCoreVersionAccessors getCore() {
            return vaccForEspressoCoreVersionAccessors;
        }

    }

    public static class EspressoCoreVersionAccessors extends VersionFactory  {

        public EspressoCoreVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: espresso.core.version (3.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("espresso.core.version"); }

    }

    public static class GradleVersionAccessors extends VersionFactory  {

        public GradleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: gradle.versions (0.51.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersions() { return getVersion("gradle.versions"); }

    }

    public static class JunitVersionAccessors extends VersionFactory  {

        public JunitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: junit.version (4.13.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("junit.version"); }

    }

    public static class MockkVersionAccessors extends VersionFactory  {

        public MockkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: mockk.version (1.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("mockk.version"); }

    }

    public static class OkhttpVersionAccessors extends VersionFactory  {

        public OkhttpVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: okhttp.version (4.11.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("okhttp.version"); }

    }

    public static class RetrofitVersionAccessors extends VersionFactory  {

        public RetrofitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: retrofit.version (2.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog versions.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("retrofit.version"); }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final GradlePluginAccessors paccForGradlePluginAccessors = new GradlePluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.gradle
         */
        public GradlePluginAccessors getGradle() {
            return paccForGradlePluginAccessors;
        }

    }

    public static class GradlePluginAccessors extends PluginFactory {

        public GradlePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for gradle.versions to the plugin id 'com.github.ben-manes.versions'
             * This plugin was declared in catalog versions.versions.toml
             */
            public Provider<PluginDependency> getVersions() { return createPlugin("gradle.versions"); }

    }

}
