package com.gigaspaces.webuitf;


public final class WebConstants {

    public final static String RED = "#ed1c24";
    public final static String RED_ORANGE = "#f26522";
    public final static String YELLOW_ORANGE = "#f7941d";
    public final static String YELLOW = "#fff200";
    public final static String PEA_GREEN = "#8dc73f";
    public final static String YELLOW_GREEN = "#39b54a";
    public final static String GREEN = "#00a651";
    public final static String GREEN_CYAN = "#00a99d";
    public final static String CYAN = "#00aeef";
    public final static String CYAN_BLUE = "#0072bc";
    public final static String BLUE = "#0054a6";
    public final static String BLUE_VIOLET = "#2e3192";
    public final static String VIOLET = "#662d91";
    public final static String VIOLET_MAGENTA = "#92278f";
    public final static String MAGENTA = "#ec008c";
    public final static String MAGENTA_RED = "#ed145b";
    public final static String GREY = "#666666";
    public static final String CHROME = "Chrome";
    public static final String FIREFOX = "Firefox";

    public static final String COLUMN_CLASS_PREFIX = "x-grid3-col-";


    public static final String SERVICE_ID_PREFIX = "__";
    public static final String SERVICE_ID_SUFFIX = "-";

    public static final class ID {

        public static final String servicesButton = "gs-tab-item-services-button";
        public static final String consoleButton = "gs-tab-item-console-button";
        public static final String monitoringButton = "gs-tab-item-monitoring-button";
        public static final String processingUnitsButton = "gs-tab-item-processing-unis-button";

        //status bar elements
        public static final String statusBarInfrastructureHosts = "status-bar-infra-hosts";
        public static final String statusBarInfrastructureGsa = "status-bar-infra-gsa";
        public static final String statusBarInfrastructureGsm = "status-bar-infra-gsm";
        public static final String statusBarInfrastructureGsc = "status-bar-infra-gsc";
        public static final String statusBarInfrastructureLus = "status-bar-infra-lus";
        public static final String statusBarInfrastructureEsm = "status-bar-infra-esm";

        public static final String statusBarServicesStateful = "status-bar-services-stateful";
        public static final String statusBarServicesStateless = "status-bar-services-stateless";
        public static final String statusBarServicesMirror = "status-bar-services-mirror";
        public static final String statusBarServicesGateway = "status-bar-services-gateway";
        public static final String statusBarServicesWeb = "status-bar-services-web";

        public static final String statusBarServicesStatefulThroughput = statusBarServicesStateful + "-throughput";
        public static final String statusBarServicesWebThroughput = statusBarServicesWeb + "-throughput";

        public static final String topologyPanel = "gs-tab-item-topology";
        public static final String dataGridPanel = "gs-tab-item-console";
        public static final String logOutButton = "gs-logout-button";
        public static final String usernameInput = "user_name-input";
        public static final String passwordInput = "password-input";
        public static final String numberOfInstInput = "num_of_instances-input";
        public static final String maxInsPerVmInput = "max_instances_per_vm-input";
        public static final String maxInstPerMachineInput = "max_inst_per_machine-input";
        public static final String dataGridNameInput = "data_grid_name-input";
        public static final String deployEDGOption = "deploy_edg_menu_item";
        public static final String deployMemcachedOption = "deploy_memcached_menu_item";
        public static final String deployProcessingUnitOption = "deploy_processing_unit_menu_item";
        public static final String deployPUButton = "deployButton";
        public static final String cancelDeploymentButton = "cancelButton";
        public static final String nextDeploymentButton = "nextButton";
        public static final String prevDeploymentButton = "prevButton";

        public static final String SLA_OVERRIDES_FIELD = "sla_override";
        public static final String ADD_CONTEXT_PROPERTY_BUTTON = "add_context_property_button";
        public static final String REMOVE_CONTEXT_PROPERTY_BUTTON = "remove_context_property_button";
        public static final String ADD_MAX_INSTANCES_PER_ZONE_BUTTON = "add_max_instances_per_zone_button";
        public static final String REMOVE_MAX_INSTANCES_PER_ZONE_BUTTON = "remove_max_instances_per_zone_button";
        public static final String SELECT_SPECIFIC_ZONES_RADIO_BUTTON = "select_specific_zone_radio_button";
        public static final String SELECT_ANY_ZONE_RADIO_RADIO_BUTTON = "select_any_radio_button";
        public static final String MAX_INSTANCES_PER_ZONE_GRID = "max_instances_per_zone__grid";
        public static final String SELECTED_ZONES_GRID = "selected_zones_grid";
        public static final String CONTEXT_PROPERTIES_GRID = "context_properties_grid";

        ////
        public static final String usernameLogginInput = "username-input";
        public static final String passwordLogginInput = "password-input";
        public static final String jiniGroupInput = "jini_group-input";
        public static final String locatorsInput = "locators-input";
        public static final String puTreeGrid = "pu_tree_grid";
        public static final String undeployPuButton = "undeploy_processing_unit";
        public static final String startGSC = "start_gsc_menu_item";
        public static final String startGSM = "start_gsm_menu_item";
        public static final String startLUS = "start_lookup_menu_item";
        public static final String spaceUrlInput = "space_url-input";
        public static final String terminateComponent = "terminate_gsa_agent_menu_item";
        public static final String restartComponent = "restart_gsa_agent_menu_item";
        public static final String restartPuInstance = "restart_pu_instance_menu_item";
        public static final String relocatePuInstance = "relocate_pu_instance_menu_item";
        public static final String relocationGrid = "gs-relocation-grid";
        public static final String relocationDialog = "gs-relocation-dialog";
        public static final String relocationSelectButton = "gs-relocation-select-button";
        public static final String alertsGridBody = "gs-alerts-grid-body";
        public static final String resourceDistribHosts = "resource_distrib-grid_null_hosts";
        public static final String resourceDistribGSA = "resource_distrib-grid_null_gsa";
        public static final String resourceDistribGSM = "resource_distrib-grid_null_gsm";
        public static final String resourceDistribGSC = "resource_distrib-grid_null_gsc";
        public static final String resourceDistribLUS = "resource_distrib-grid_null_lus";
        public static final String moduleWeb = "modules-status-grid_null_web";
        public static final String moduleStateful = "modules-status-grid_null_stateful";
        public static final String moduleStateless = "modules-status-grid_null_stateless";
        public static final String moduleMirror = "modules-status-grid_null_mirror";
        public static final String moduleCustom = "modules-status-grid_null_custom";

        public static final String moduleLoadBalancer    = "modules-status-grid_null_loadBalancer";
        public static final String moduleWebServer       = "modules-status-grid_null_webServer";
        public static final String moduleSecurityServer  = "modules-status-grid_null_securityServer";
        public static final String moduleAppServer       = "modules-status-grid_null_appServer";
        public static final String moduleEsbServer       = "modules-status-grid_null_esbServer";
        public static final String moduleMessageBus      = "modules-status-grid_null_messageBus";
        public static final String moduleDatabase        = "modules-status-grid_null_database";
        public static final String moduleNoSqlDb         = "modules-status-grid_null_noSqlDb";

        //public static final String logsPanel = "gs-tab-item-logs";
        public static final String hostsLogs = "gs-hosts-logs";

        public static final String alertsButton = "alerts-button";
        public static final String eventsButton = "events-button";

        // recipes panel
        public static final String recipesPanel = "gs-tab-item-topology-recipes";
        public static final String recipesSelectionTree = "gs-recipes-selection-tree";
        public static final String recipesSelectionTreeFolderNodePrefix = WebConstants.ID.recipesSelectionTree + "_folder_";
        public static final String recipesSelectionTreeFileNodePrefix = WebConstants.ID.recipesSelectionTree + "_file_";

        public static final String habytesPerSecond = "high-avail-grid_null_sendBytesPerSec";
        public static final String hapacketsPerSecond = "high-avail-grid_null_sendPacketsPerSec";
        public static final String edsBytesPerSecond = "eds-grid_null_sendBytesPerSec";
        public static final String edsPacketsPerSecond = "eds-grid_null_sendPacketsPerSec";
        public static final String edsOpPerSecond = "eds-grid_null_successfulOperationsPerSec";

        public static final String criticalIcon = "gs-status-icon-critical";
        public static final String okIcon = "gs-status-icon-ok";
        public static final String warningIcon = "gs-status-icon-alert";
        public static final String naIcon = "gs-status-icon-na";
        public static final String niIcon = "gs-status-icon-not-initialized";

        public static final String applicationsMenuPanel = "gs-applications-menu-panel";
        public static final String graphApplicationMap = "gs-graph-application-map";
        //		public static final String graphApplicationMapClearSelection = "gs-graph-application-map-clear-capturer";
        public static final String graphGatewaysTopology = "gs-graph-gateways-topology";

        public static final String statusGrid = "gs-window-grid-status";

        public static final String stateMemoryPanel = "gs-state-memory-panel";
        public static final String stateCpuPanel = "gs-state-cpu-panel";

        public static final String stateMemoryUsageValue = "gs-state-panel-usage-memory";
        public static final String stateCpuUsageValue = "gs-state-panel-usage-cpu";

        public static final String nodeStatusOk = "ok";
        public static final String nodeStatusWarning = "warn";
        public static final String nodeStatusBroken = "critical";
        public static final String nodeStatusWorking = "working";

        public static final String puGridLocalCachesInDataGridMenuItem = "local_caches_in_data_grid_menu_item";
        public static final String puGridLocalViewsInDataGridMenuItem = "local_views_in_data_grid_menu_item";
        public static final String puGridRemoteSpaceInDataGridMenuItem = "remote_space_in_data_grid_menu_item";
        public static final String puGridRemoteServicesInDataGridMenuItem = "remote_services_in_data_grid_menu_item";
        public static final String puGridEventContainersInDataGridMenuItem = "event_containers_in_data_grid_menu_item";
        public static final String puGridUninstallMenuItem = "gs-menu-item-uninstall";
        public static final String puGridViewInDataGridMenuItem = "view_in_data_grid_menu_item";
        public static final String puGridRestartPuInstanceMenuItem = "restart_pu_instance_menu_item";
        public static final String puGridRelocatePuInstanceMenuItem = "relocate_pu_instance_menu_item";
        public static final String puGridGenerateServicesDumpMenuItem = "genereate_dump_menu_item";

		/*
            'ok': 'status-ok.png',
            'warn': 'status-alert.png',
            'critical': 'status-critical.png',
            'empty': 'status-empty.png',
            'working': 'spinner.gif'
		 */

        public static final String nodePath = "node-shape-path-";
        public static final String detailsPanel = "gs-side-panel-details";
        public static final String serviceDetails = "gs-tab-item-service-details";

        /* TopologyTab sub panel toggles */
        public static final String physicalPanelToggle = "gs-tab-item-physical-toggler-button";
        public static final String logsPanelToggle = "gs-tab-item-logs-toggler-button";
        public static final String logicalPanelToggle = "gs-tab-item-logical-toggler-button";

        /* DataGridTab sub panel toggles */
//		public static final String connectionsPanelToggle = "gs-tab-item-toggler-button";
//		public static final String instancesPanelToggle = "gs-tab-item-toggler-button";
        public static final String queriesPanelToggle = "gs-tab-item-queries-toggler-button";
        public static final String statisticsPanelToggle = "gs-tab-item-statistics-toggler-button";
        //		public static final String templatesPanelToggle = "gs-tab-item-toggler-button";
        public static final String typesPanelToggle = "gs-tab-item-types-toggler-button";
        public static final String gatewaysPanelToggle = "gs-tab-item-gateways-toggler-button";
        public static final String clientSideCachesPanelToggle = "gs-tab-item-client-side-caches-toggler-button";
        public static final String eventContainersPanelToggle = "gs-tab-item-event-containers-toggler-button";
        public static final String configurationPanelToggle = "gs-tab-item-configuration-toggler-button";
        public static final String networkPanelToggle = "gs-tab-item-network-toggler-button";
        public static final String transactionsPanelToggle = "gs-tab-item-transactions-toggler-button";


        public static final String gatewaysOutboundTogglerButton = "gs-tab-item-outbound-toggler-button";
        public static final String gatewaysInboundTogglerButton = "gs-tab-item-inbound-toggler-button";
        public static final String gatewaysGraphicalTogglerButton = "gs-tab-item-graphical-toggler-button";

        public static final String asyncPollingEventContainersTogglerButton = "gs-tab-item-asyncpolling-event-containers-toggler-button";
        public static final String pollingEventContainersTogglerbutton = "gs-tab-item-polling-event-containers-toggler-button";
        public static final String notifyEventContainersTogglerbutton = "gs-tab-item-notify-event-containers-toggler-button";

        public static final String localViewsPanelToggle = "gs-tab-item-local-views-toggler-button";
        public static final String localCachesPanelToggle = "gs-tab-item-local-caches-toggler-button";

        public static final String inboundActivityPanelToggle = "gs-tab-item-inbound-activity-toggler-button";
        public static final String notificationsActivityPanelToggle = "gs-tab-item-notifications-activity-toggler-button";

        public static final String physicalPanel = "gs-tab-item-physical";
        public static final String spaceInstancesGrid = "gs-space-instances-grid";
        public static final String spaceConfigurationGrid = "gs-space-info-grid";
        public static final String spaceTypesGrid = "gs-space-types-grid";
        public static final String spaceTransactionsGrid = "gs-space-transactionspanel-grid";
        public static final String sidePanelSpacesTree = "gs-side-panel-space-tree";
        public static final String goToLogsItemID = "gs-logical-navigate_to_logs";
        public static final String alertsGridDumpItem = "gs-alerts-grid-dump";
        public static final String restartPuInstanceItem = "gs-logical-restart-pu-instance";
        public static final String logoutButton = "gs-logout-button";
        public static final String resourceDistribESM = "resource_distrib-grid_null_esm";
        public static final String comparisonMetricTop = "comparisonMetricPanelTop";
        public static final String comparisonMetricBottom = "comparisonMetricPanelBottom";

        public static final String morePopup = "gs-popup-grid-instances";
        public static final String topologyEventsGrid = "topology-events-grid";
        public static final String dashboardEventsGrid = "dahsboard-events-grid";

        public static final String hostsGrid = "hosts_tree_grid";
        public static final String hostsSummaryButtonId = "gs-hosts-menu-item-summary-button";
        public static final String hostsLogsButtonId = "gs-hosts-menu-item-logs-button";

        public static final String processingUnitsGrid = "processing_units_tree_grid";

        public static final String processingUnitsEventsButtonId = "gs-pus-menu-item-events-button";
        public static final String processingUnitsHostsButtonId = "gs-pus-menu-item-hosts-button";
        public static final String processingUnitsLogsButtonId = "gs-pus-menu-item-logs-button";
        public static final String processingUnitsConfigButtonId = "gs-pus-menu-item-config-button";

        public static final String undeployApplicationButton = "gs-button-uninstall-app";

        public static final String metricPopupHeader = "gs-balance-dialog-panel-north";
        public static final String metricPopupLow = "gs-balance-dialog-panel-center";
        public static final String metricPopupHigh = "gs-balance-dialog-panel-east";

        public static final String metricPopup = "gs-balance-dialog";

        public static final String queryNavigatorNext = "gs-query-navigator-button-forward";
        public static final String queryNavigatorPrev = "gs-query-navigator-button-back";
        public static final String queryNavigatorRun = "gs-query-navigator-button-go";
        public static final String queryResultsGrid = "gs-query-results-grid";

        public static final String sliderDataTypes = "gs-slider-data-types";

        public static final String controllerLogs = "gs-controllers-logs";

        public static final String servicesDumpWindow = "gs-services-dump-window";

        public static final String topologyLastMinuteEventsButton = "topology-events-last-minute-time-button";
        public static final String topologyLastHourEventsButton = "topology-events-last-hour-button";
        public static final String topologyLastDayEventsButton = "topology-events-last-day-button";
        public static final String topologyAllEventsButton = "topology-events-all-button";
        public static final String topologyEventsFilter = "topology-events-filter";
        public static final String topologyEventsFilterInput = "topology-events-filter-input";

        public static final String dashboardLastMinuteEventsButton = "dashboard-events-last-minute-time-button";
        public static final String dashboardLastHourEventsButton = "dashboard-events-last-hour-button";
        public static final String dashboardLastDayEventsButton = "dashboard-events-last-day-button";
        public static final String dashboardAllEventsButton = "dashboard-events-all-button";
        public static final String dashboardEventsFilter = "dashboard-events-filter";
        public static final String dashboardEventsFilterInput = "dashboard-events-filter-input";


        public static final String monitoringUnavailableNotAuthorized = "monitoring-unavailable-not-authorized";
        public static final String monitoringUnavailableDbNotDefined = "monitoring-unavailable-db-not-defined";
        public static final String monitoringUnavailableNotInitialized = "monitoring-unavailable-not-initialized";

        public static String getActionToolBoxId(String name) {
            return "node-tool-ACTIONS-" + name;
        }
        public static String getInfoToolBoxId(String name) {
            return "node-tool-INFO-" + name;
        }

        public static String getPuBoxRectId(String puName) {
            return "gs-color-box-" + puName;
        }

        public static String getPuNodeId(String nodeName) {
            return "gs-services-logs-selection-tree_pu_name_" + nodeName;
        }
        public static String getLogsMachineId(String machineName, String puName) {
            return "gs-services-logs-selection-tree_" + puName + "_" + machineName;
        }
        public static String getLogServiceId(String serviceName) {
            return "gs-services-logs-selection-tree_" + serviceName;
        }
        public static String getControllerLogServiceId(String serviceName) {
            return "gs-controller-logs-selection-tree_" + serviceName;
        }
        public static String getPuInstanceId(String name) {
            return "gs-logical-grid_" + name;
        }

        public static String getHostId(String name) {
            return "gs-physical-grid_" + name;
        }
        public static String getLogsContianerId(String contianerId, String agentId,
                                                String uid,String puName) {
            return "gs-services-logs-selection-tree_" + puName + "_gsc-" + agentId + "[" + contianerId + "]" + "__-" + uid;
        }
        public static String getMoreButtonId(String ip) {
            return "gs-more-button-" + ip;
        }
    }

    public static final class Xpath {
        public static final String pathToTopologyPanel = "//div[@id='" + ID.topologyPanel + "']";
        public static final String deployMenuButton = "//table[@id='deploy_button']/tbody/tr[2]/td[2]/em";
        public static final String clusterSchemaCombo = "//div[@id='cluster_schema']/img";
        public static final String numberOfBackupsInc = "//div[@id='num_of_backups']/span/img[1]";
        public static final String isSecuredCheckbox = "//div[@id='secured_check_box']/input";
        public static final String loginButton = "//table[@id='login_button']";
        public static final String discoveryLegend = "//legend/div";
        public static final String deoployFromListRadioButton = "//div[@id='pu_list_rb']/input";
        public static final String existingPuCombo = "//div[@id='existing_list_of_pu']/img";
        public static final String okAlert = "//button[text()='OK']";
        public static final String acceptAlert = "//button[text()='Yes']";
        public static final String dismissAlert = "//button[text()='No']";
        public static final String pathToAlerts = "//div[@id='gs-alerts-grid-body']";
        public static final String pathToAlertSeverity = "/table/tbody/tr/td[1]";
        public static final String pathToAlertType = "/table/tbody/tr/td[2]";
        public static final String pathToAlertDescription = "/table/tbody/tr/td[3]";
        public static final String pathToAlertLocation = "/table/tbody/tr/td[4]";
        public static final String pathToAlertLastUpdated = "/table/tbody/tr/td[5]";
        public static final String pathToAlertExpansionButton = "/table/tbody/tr/td[1]/div/div/div/img[2]";
        public static final String pathToHosts = "//div[@id='" + ID.resourceDistribHosts + "']";
        public static final String pathToGSA = "//div[@id='" + ID.resourceDistribGSA + "']";
        public static final String pathToGSM = "//div[@id='" + ID.resourceDistribGSM + "']";
        public static final String pathToGSC = "//div[@id='" + ID.resourceDistribGSC + "']";
        public static final String pathToLUS = "//div[@id='" + ID.resourceDistribLUS + "']";
        public static final String pathToESM = "//div[@id='" + ID.resourceDistribESM + "']";
        public static final String pathToWebModule = "//div[@id='" + ID.moduleWeb + "']";
        public static final String pathToStatefullModule = "//div[@id='" + ID.moduleStateful + "']";
        public static final String pathToStatelessModule = "//div[@id='" + ID.moduleStateless + "']";
        public static final String pathToMirrorModule = "//div[@id='" + ID.moduleMirror + "']";
        public static final String pathToCustomModule = "//div[@id='" + ID.moduleCustom + "']";

        public static final String pathToLoadBalancerModule = "//div[@id='" + ID.moduleLoadBalancer + "']";
        public static final String pathToAppServerModule = "//div[@id='" + ID.moduleAppServer + "']";
        public static final String pathToWebServerModule = "//div[@id='" + ID.moduleWebServer + "']";
        public static final String pathToSecurityServerModule = "//div[@id='" + ID.moduleSecurityServer + "']";
        public static final String pathToEsbServerModule = "//div[@id='" + ID.moduleEsbServer + "']";
        public static final String pathToMessageBusModule = "//div[@id='" + ID.moduleMessageBus + "']";
        public static final String pathToDatabaseModule = "//div[@id='" + ID.moduleDatabase + "']";
        public static final String pathToNoSqlDbModule = "//div[@id='" + ID.moduleNoSqlDb + "']";

        public static final String pathTohaBytesPerSecond = "//div[@id='" + ID.habytesPerSecond + "']";
        public static final String pathTohaPacketsPerSecond = "//div[@id='" + ID.hapacketsPerSecond + "']";
        public static final String pathToEDSPacketsPerSecond = "//div[@id='" + ID.edsPacketsPerSecond + "']";
        public static final String pathToEDSBytesPerSecond = "//div[@id='" + ID.edsBytesPerSecond + "']";
        public static final String pathToEDSOpPerSecond = "//div[@id='" + ID.edsOpPerSecond + "']";

        public static final String pathToNumberInResourcesPanelOfGridStatus = "/div[2]/div/table/tbody/tr[1]/td[2]";
        public static final String pathToNumberInResourceGrid = "/table/tbody/tr/td[3]";
        public static final String pathToNameInResourceGrid = "/table/tbody/tr/td[4]";
        public static final String pathToIconInResourceGrid = "/table/tbody/tr/td[1]/div/span";
        public static final String pathToNumberInModulesGrid = "/table/tbody/tr/td[3]";
        public static final String pathToIconInModulesGrid = "/table/tbody/tr/td[1]/div/span";
        public static final String pathToIconInHighAvGrid = "/table/tbody/tr/td[1]/div/span";
        public static final String pathToCountInHighAvGrid = "/table/tbody/tr/td[2]/div/span";
        public static final String pathToNumberInEDSGrid = "/table/tbody/tr/td[2]";
        public static final String pathToIconInEDSGrid = "/table/tbody/tr/td[1]/div/span";
        public static final String pathToAlertIcon = "/table/tbody/tr/td[1]/div/div/div/span[3]/span[1]";
        public static final String pathToStatusColumnInAlertsGrid = "//div[1]/div/div[3]/div[2]/div[1]/div/div/div[1]/div[1]/div[1]/div[1]/div/div/table/tbody/tr/td[1]/div/span";
        public static final String pathToApplicationNameInPanel = "/table/tbody/tr/td[1]/div/span";
        public static final String pathToApplicationInstancesInPanel = "/table/tbody/tr/td[2]/div/span";
        public static final String pathToApplicationMap = "//div[@id='" + ID.graphApplicationMap + "']/svg";
        public static final String pathToPuTreeGrid = "//div[@id='" + ID.puTreeGrid + "']";
        public static final String pathToPuName = "/table/tbody/tr/td[1]";
        public static final String pathToPuType = "/table/tbody/tr/td[3]";
        public static final String pathToPuPlannedInstances = "/table/tbody/tr/td[4]";
        public static final String pathToPuActualInstances = "/table/tbody/tr/td[5]";
        public static final String pathToPuStatus = "/table/tbody/tr/td[2]/div/img";
        public static final String pathToPuOptionsButton = "/table/tbody/tr/td[1]/div/div/div/span[3]/a/img";
        public static final String pathToMetricType = "//div/span";
        public static final String pathToMetricBalance = "div[2]/div/div[2]";
        //		public static final String pathToTopologySubPanel = "//div[@id='" + ID.TopologySubPanel + "']";
        public static final String pathToApplicationComboBox = "";
        public static final String pathToDetailsPanel = "//div[@id='" + ID.serviceDetails + "']";
        public static final String pathToServiceDetails = "//li[1]/img";
        public static final String pathToComparisonCharts = "//li[2]/img";
        public static final String pathToAttributeName = "/table/tbody/tr/td[1]/div/span";
        public static final String pathToAttributeValue = "/table/tbody/tr/td[2]/div/span";
        public static final String pathToApplicationMenuButtonSelection = "/div/div/div/div/table/tbody/tr/td/div/img";
        public static final String pathToGroupName = "/div[1]/div/span";
        public static final String pathToTopologySubPanelName = "//div/label";
        public static final String pathToPhysicalPanelButton = "//table[@id='" + ID.physicalPanelToggle + "']/tbody/tr[2]/td[2]/em/button";
        public static final String pathToPhysicalPanel = "//div[@id='" + ID.physicalPanel + "']";
        public static final String pathToOs = "/div/table/tbody/tr/td[2]/div/img";
        public static final String pathToHostName = "/table/tbody/tr/td[3]";
        public static final String pathToCores = "/table/tbody/tr/td[6]";
        public static final String pathToPUIs = "/table/tbody/tr/td[7]";
        public static final String pathToGSCCount = "/table/tbody/tr/td[8]";
        public static final String pathToGSACount = "/table/tbody/tr/td[9]";
        public static final String pathToGSMCount = "/table/tbody/tr/td[10]";
        public static final String pathToUndeployNode = "//a[contains(text(),'Undeploy')]";
        public static final String pathToGenerateNodeDump = "//a[contains(text(),'Generate dump')]";
        public static final String generateDumpButton = "//button[text()='Generate']";
        public static final String closeWindow = "//button[text()='Close']";
        public static final String pathToTopMetricSelection = "//div[@id='" + ID.detailsPanel + "']/div[2]/div/div/div[2]/div/div/div/div[1]";
        public static final String pathToBottomMetricSelection = "//div[@id='" + ID.detailsPanel + "']/div[2]/div/div/div[2]/div/div/div/div[3]";
        //		public static final String pathToBottomMetric = "//div[@id='" + ID.bottomMetric + "']/div";
//		public static final String pathToTopMetric = "//div[@id='" + ID.topMetric + "']/div";
        public static final String pathToSpaceInstanceGrid = "//div[@id='" + ID.spaceInstancesGrid + "']";
        public static final String pathToSpaceConfigurationGrid = "//div[@id='" + ID.spaceConfigurationGrid + "']";
        public static final String pathToSpaceTypesGrid = "//div[@id='" + ID.spaceTypesGrid + "']";
        public static final String pathToSpaceTransactionsGrid = "//div[@id='" + ID.spaceTransactionsGrid + "']";
        public static final String pathToSpaceTreeSidePanel = "//div[@id='" + ID.sidePanelSpacesTree + "']";
        public static final String pathToLogsSamplingButton = "//div[@id='gs-services-logs-tool-bar']/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button";
        public static final String pathToApplicationNameInLogsPanel = "//div[@id='gs-tab-item-logs']/div/div/div[1]/span";
        public static final String pathToLogsMachineExpandButton = "/div[1]/img[2]";

        public static final String pathToInstanceNameInExtremeMetricCases = "/td[1]";
        public static final String pathToHostNameInExtremeMetricCases = "/td[2]";
        public static final String pathToMetricValueInExtremeMetricCases = "/td[3]";

        public static final String pathToMoreButton = "//div/div/div[2]";
        public static final String pathToPuIBoxes = "//div/div/div[1]";

        public static final String pathToMetricPopupTitle = "//div[@id='" + ID.metricPopupHeader + "']/div[1]";
        public static final String pathToMetricPopupAverageValue = "//div[@id='" + ID.metricPopupHeader + "']/div[2]";

        public static final String pathToMetricPopupOKButton = "//div[@id='" + ID.metricPopup + "']/div[1]";
        public static final String pathToQueryNavigatorShowNextButton = "//table[@id='" + ID.queryNavigatorNext + "']/tbody/tr[2]/td[2]/em";
        public static final String pathToQueryNavigatorPrevButton = "//table[@id='" + ID.queryNavigatorPrev + "']/tbody/tr[2]/td[2]";
        public static final String pathToQueryNavigatorNextButton = "//table[@id='" + ID.queryNavigatorNext + "']/tbody/tr[2]/td[2]";
        public static final String pathToQueryNavigatorShowPrevButton = "//table[@id='" + ID.queryNavigatorPrev + "']/tbody/tr[2]/td[2]/em";

        public static final String pathToEventText = "div/span";

        public static final String pathToServicesRegularText = "div/span";
        public static final String pathToServicesNameText = "div/div/div/span/span";

        public static final String relativePathToSpaceModeImage = "img";

        public static final String getPathToMetricHoverMenuOption(String menu) {
            return "//a[text()='" + menu.replace("_", " ") + "']";
        }

        public static String getPathToPuInContextPager(String puOrPuiName) {
            return "//div[text()='" + puOrPuiName + "']";
        }

        public static final String getPathToMetricExtremeLowCasesRow(int rowIndex) {
            return "//div[@id='" + ID.metricPopupLow + "']/div[2]/div/div/div/div/div/div[2]/div[" + rowIndex + "]/div/table/tbody/tr";
        }

        public static final String getPathToMetricExtremeHighCasesRow(int rowIndex) {
            return "//div[@id='" + ID.metricPopupHigh + "']/div[2]/div/div/div/div/div/div[2]/div[" + rowIndex + "]/div/table/tbody/tr";
        }

        public static String getPathToEventInTopologyEventsGrid(int index) {
            return "//div[@id='" + ID.topologyEventsGrid + "']/div/div/div[2]/div/div[" + index + "]";
        }

        public static final String getPathToInst(int i) {
            return "//div[@id='" + ID.morePopup + "']/div/div[1]/div[2]/div/div[" + i + "]";
        }

        public static final String getPathToMenuSelection(String st) {
            return "//a[contains(text(),'" + st + "')]";
        }


        public static final String getPathToAlertByIndex(int index) {
            return Xpath.pathToAlerts + "/div[" + index + "]";
        }

        public static String getPathToHostsGridRowNumber(int index) {
            return "//*[@id=\"hosts_tree_grid\"]/div[1]/div[1]/div[2]/div/div[" + index + "]/table/tbody/tr[1]/td[1]/div/div/div/span/span";
        }

        public static String getPathToProcessingUnitsRowNumber(int index) {
            //return "//*[@id=\"processing_units_tree_grid\"]/div[1]/div[1]/div[2]/div/div[" + index + "]/table/tbody/tr[1]/td[1]/div/div/div/span/span";
            return "//*[@id=\"processing_units_tree_grid\"]/div/div/div[2]/div/div[" + index + "]/table/tbody/tr/td/div/div/div/span[2]/span";
        }

        public static String getPathToProcessingUnitsRowToolsButton(int index) {
            //return "//*[@id=\"processing_units_tree_grid\"]/div[1]/div[1]/div[2]/div/div[" + index + "]/table/tbody/tr[1]/td[1]/div/div/div/span/span";
            return "//*[@id=\"processing_units_tree_grid\"]/div/div/div[2]/div/div[" + index + "]/table/tbody/tr/td[10]/div/table";///tbody/tr[2]/td[2]/em/button
        }

        public static String getPathToProcessingUnitsRowPuType(int index) {
            return "//*[@id=\"processing_units_tree_grid\"]/div/div/div[2]/div/div[" + index + "]/table/tbody/tr/td[3]/div/span";
        }

        public static String getPathToProcessingUnitsRowStatus(int index) {
            return "//*[@id=\"processing_units_tree_grid\"]/div/div/div[2]/div/div[" + index + "]/table/tbody/tr/td[2]/div/span";
        }

        public static String getPathToComboSelection(String selection) {
            return "//div[text()='" + selection + "']";
        }

        public static String getPathToComboSelectionInServicesTab(String selection) {
            return "//div[text()='" + selection + "']";
        }

        public static final String getPathToHeaderEventInDashboardEventsGrid(int index) {
            return "//div[@id='" + ID.dashboardEventsGrid + "']/div/div/div[2]/div/div[" + index + "]";
        }

        public static final String getPathToHeaderServicesGrid(int index) {
            return "//div[@id='" + ID.hostsGrid + "']/div/div/div[2]/div/div[" + index + "]";
        }

        public static final String getPathToHeaderProcessingUnitsGrid(int index) {
            return "//div[@id='" + ID.processingUnitsGrid + "']/div/div/div[2]/div/div[" + index + "]";
        }
        public static final String getPathToTypeElement( String id ) {
            return "//td[@id='" + id + "']/div/div";
        }


        public static String getPathToGsaOption(String id) {
            return "//div[@id='" + id + "']/div/span[3]/a/img";
        }

        public static String getPathToGscOption(String id) {
            return "//div[@id='" + id + "']/div/span[3]/a/img";
        }

        public static String getPathToHostnameOptions(String id) {
            return "//div[@id='" + id + "']/div/img[2]";
        }

        public static String getPathToUndeployPuButton(String processingUnitName) {
            return "//div[@id='pu_tree_grid_pu_" + processingUnitName + "']/div/span[3]/a/img";
        }

        public static String getPathToProcessingUnitByIndex(int index) {
            return Xpath.pathToPuTreeGrid + "/div/div/div[2]/div/div[" + index + "]";
        }

        public static String getPathToPartition(int partition, String puName) {
            return "//div[@id='pu_tree_grid_" + puName + "_partition " + partition + "']/div/img[2]";
        }

        public static String getPathToInstanceOptions(int partition, String puName) {
            return "//div[@id='pu_tree_grid_" + puName + "_" + puName + ". " + partition + " [1]']/div/span[3]/a/img";
        }

        public static String getPathToNumberOfHosts() {
            return "//div[@id='" + ID.resourceDistribHosts + "']/table/tbody/tr/td[3]";
        }

        public static String getPathToNumberOfGSA() {
            return "//div[@id='" + ID.resourceDistribGSA + "']/table/tbody/tr/td[3]";
        }

        public static String getPathToNumberOfGSM() {
            return "//div[@id='" + ID.resourceDistribGSM + "']/table/tbody/tr/td[3]";
        }

        public static String getPathToNumberOfGSC() {
            return "//div[@id='" + ID.resourceDistribGSC + "']/table/tbody/tr/td[3]";
        }

        public static String getPathToNumberOfLUS() {
            return "//div[@id='" + ID.resourceDistribLUS + "']/table/tbody/tr/td[3]";
        }

        public static String getPathToNumberOfWebModules() {
            return "//div[@id='" + ID.moduleWeb + "']/table/tbody/tr/td[2]";
        }

        public static String getPathToNumberOfStatefullModules() {
            return "//div[@id='" + ID.moduleStateful + "']/table/tbody/tr/td[2]";
        }

        public static String getPathToNumberOfStatelessModules() {
            return "//div[@id='" + ID.moduleStateless + "']/table/tbody/tr/td[2]";
        }

        public static String getPathToNumberOfMirrorModules() {
            return "//div[@id='" + ID.moduleMirror + "']/table/tbody/tr/td[2]";
        }

        public static String getPathToApplicationInPanel(int index) {
            return "//div[@id='" + ID.applicationsMenuPanel  +  "']/table[" + index + "]";
        }

        public static String getPathToApplicationSelectionButton(int appIndex) {
            return getPathToApplicationInPanel(appIndex) + "/tbody/tr[2]/td[2]/em/button";
        }

        public static String getPathToApplicationSelectionButton(String appName) {
            return "//button[text()='" + appName + "']";
        }

        public static String getPathToPuInstanceButton(String name, int partition) {
            if (partition == 0) {
                return "//div[@id='pu_tree_grid_" + name + "_" + name + "[1]']/div/span[3]/a/img";
            }
            else return "//div[@id='pu_tree_grid_" + name + "_" + name + "." + partition  + " " + "[1]" + "']/div/span[3]/a/img";
        }

        public static String getPathToPuButton(String name) {
            return "//div[@id='pu_tree_grid_pu_name_" + name + "']/div/img[2]";
        }

        public static String getPathToPartitionButton(String name, int partition) {
            return "//div[@id='pu_tree_grid_" + name + "_partition " + partition + "']/div/img[2]";
        }

        public static String getPathToDetailsGroup(int i) {
            return Xpath.pathToDetailsPanel + "/div/div/div/div/div[2]/div/div[" + i + "]";
        }

        public static String getPathToAttributeInGroup(int j) {
            return "/div[2]/div[" + j + "]";
        }

        public static String getPathToHostByIndex(int i) {
            return Xpath.pathToPhysicalPanel + "/div/div[2]/div/div/div/div/div[2]/div[" + i + "]";
        }


        public static String getPathToSpaceInstanceByIndex(int i) {
            return Xpath.pathToSpaceInstanceGrid + "/div/div/div[2]/div/div[" + i + "]";
        }


        public static String getPathToSpaceTreeNodeByIndex(int i) {
            return Xpath.pathToSpaceTreeSidePanel + "/div/table/tbody/tr[3]/td/div/div[" + i + "]";
        }
        public static String getPathToPuLogsExapndButton(String puName) {
            return "//div[@id='" + ID.getPuNodeId(puName) + "']/div[1]/img[2]";
        }
        public static String getPathToLogsMachine(String machineName, String puName) {
            return "//div[@id='" + ID.getLogsMachineId(machineName, puName) + "']";
        }

        public static String getPathToLogsContianer(String contianerId, String agentId, String uid, String puName) {
            return "//div[@id='" + ID.getLogsContianerId(contianerId, agentId, uid, puName) + "']";
        }

        public static String getPathToLogsMachineServiceByIndex(int i) {
            return "/div[2]/div[" + i + "]";
        }

        public static String getPathToLogsContianerPuInstanceByIndex(int i) {
            return "/div[2]/div[" + i + "]";
        }


        public static final String getPathToGatewaysInboundByIndex(int index) {
            return "/div[1]/div[1]/div[2]/div[" + index + "]";
        }
    }

    public static final class DOM {


    }

    public static final class JQuery {

        public static final String puIntnacesPhysicalTabSelector = "$('div.x-grid3-cell-inner.x-grid3-col-processing_unit_instances > div > svg > text > tspan').text()";

        public static String getMouseOverMetricTypeScript(String type) {
            return "$(" + '"' + "a:contains('" + type + "')" + '"' + ").trigger('mousover')";
        }

        public static String getMetricBalanceScript(String type) {
            return "return $('." + "gs-metric-title-" + type + " text:last').text()";
        }

        public static String getKeyPressScript(int keyCode) {

            return "return jQuery.fn.simulateKeyPress = function(keycode) " +
                    "{jQuery(this).trigger({ type: 'keypress', which: keycode });" +
                    "};" +
                    "jQuery(document).ready( function($) " +
                    "{$( 'body' ).keypress( function(e) " +
                    "{" +
                    "console.log(e);" +
                    "alert(e)" +
                    "});" +
                    "$( 'body' ).simulateKeyPress(" + keyCode + ");" +
                    "}" +
                    ");";
        }

    }

    public static final class Attributes{

        public final static String UNFORMATTED_VALUE_ATTRIBUTE = "unformatted-value";
    }

    public static final class ClassNames {

        public static final String primarySpaceImage = "gs-space-instance-icon-primary";
        public static final String backupSpaceImage = "gs-space-instance-icon-backup";
        public static final String selectedItemInLogsTree = "x-ftree2-selected";
        public static final String barLineChartContainer = "highcharts-container";
        public static final String balanceGauge = "gs-balance-gauge";

        public static final String statePanelHeader = "gs-state-panel-header";

        public static final String runmodePanel = "gs-runmode-panel";

        public static final String EventsGridEventTitle = "x-grid3-td-component_name";
        public static final String EventsGridEventMessage = "x-grid3-td-message";
        public static final String EventsGridEventDescription = "x-grid3-td-description";
        public static final String EventsGridEventTime = "x-grid3-td-date";
        public static final String EventsGridEventStatus = "x-grid3-td-status";
        public static final String EventsGridClearFilter = "x-form-clear-trigger";

        public static final String ServicesGridNameCell = "x-grid3-td-name";
        public static final String ServicesGridPuTypeCell = "x-grid3-td-puType";
        public static final String ServicesGridApplicationNameCell = "x-grid3-td-applicationName";
        public static final String ServicesGridZonesCell = "x-grid3-td-zones";
        public static final String ServicesGridThreadCountCell = "x-grid3-td-threadCount";
        public static final String ServicesGridGridServicesCountCell = "x-grid3-td-gridServicesCounts";
        public static final String ServicesGridProcessingUnitInstanceCountCell = "x-grid3-td-processingUnitsInstancesCounts";
        public static final String ServicesGridPrimaryBackupCell = "x-grid3-td-primaryBackupsCounts";

        public static final String win32OS = "gs-os-icon-Win32";

        public static final String codeEditor = "CodeMirror";

        public static final String contextPagerDataMode = "x-grid3-col-tree_browser_model_data_name";
        public static final String contextPagerNextButton = "x-grid3-col-tree_browser_model_data_next_button";

        public static final String applicationContextPanel = "gs-window-app-context";

        public static final String buttonGenerate = "gs-button-generate";
        public static final String buttonClose = "gs-button-close";

        public static final String dataGridSpaceMode = "x-grid3-col-space_mode_icon";
        public static final String topologySpaceMode = "x-grid3-col-icon";

        public static final String lookupGroups = "gs-groups-label";
        public static final String lookupLocators = "gs-locators-label";

        public static final String GS_SIDE_MENU_PROCESSING_UNITS_VIEW_LOGS = "gs-side-menu-processing-units-view-logs";
        public static final String GS_SIDE_MENU_HOSTS_VIEW_LOGS = "gs-side-menu-hosts-view-logs";

        public static final String getHostClassName(String name) {
            return "gs-physical-grid-row-" + name;
        }


        public static final String processingUnitsViewConfig = "gs-side-menu-processing-units-view-config";

        public static final String dictionaryKey = "gs-dictionary-key";
        public static final String dictionaryValue = "gs-dictionary-value";

        public static final String alertsAndEventsLabel = "gs-alerts-events-num-label";
        public static final String alertsOnlyLabel = "gs-alerts-num-label";
        public static final String eventsOnlyLabel = "gs-events-num-label";

        /**
         * @deprecated Use {@link #getMetricClassNameByIndex(int)} instead.
         */
        @Deprecated
        public static final String getMetricClassName(String name) {
            return "gs-metric-title-" + name;
        }

        public static final String getMetricClassNameByIndex(int index) {
            return "gs-monitoring-component-" + index;
        }

        public static String getPuInstanceClassName(String name) {
            return "gs-physical-grid-row-" + name.replaceAll(" ", "").replaceAll("\\.", "_");
        }

        public static String getEventClassNameByIndex(int index) {
            return "";
        }

    }

    public static final class METRIC_CLASS_NAMES {

        private static final String GS_METRIC_TITLE = "gs-metric-title-";
        private static final String DELIMETER = "_";

        public static final String VM_CPU_METRIC_NAME = GS_METRIC_TITLE  + "VM" + DELIMETER +  "CPU";
        public static final String VM_MEMORY_METRIC_NAME = GS_METRIC_TITLE  + "VM" + DELIMETER +  "Memory";
        public static final String VM_GC_METRIC_NAME = GS_METRIC_TITLE  + "VM" + DELIMETER +  "GC";

        public static final String OS_CPU_METRIC_NAME = GS_METRIC_TITLE  + "OS" + DELIMETER +  "CPU";
        public static final String OS_MEMORY_METRIC_NAME = GS_METRIC_TITLE  + "OS" + DELIMETER +  "Memory";

        public static final String SPACE_WRITE_THROUGHPUT_METRIC_NAME = GS_METRIC_TITLE  + "SPACE" + DELIMETER +  "Write_per_sec.";
        public static final String SPACE_READ_THROUGHPUT_METRIC_NAME = GS_METRIC_TITLE  + "SPACE" + DELIMETER +  "Read_per_sec.";
        public static final String SPACE_REPLICATION_PER_SEC = GS_METRIC_TITLE  + "SPACE" + DELIMETER +  "Replication_(bytes/sec)";
        public static final String SPACE_TAKE_THROUGHPUT_METRIC_NAME = GS_METRIC_TITLE  + "SPACE" + DELIMETER +  "Take_per_sec.";

        public static final String WEB_HTTP_THROUGHPUT = GS_METRIC_TITLE + "WEB_REQUESTS" + DELIMETER + "HTTP_throughput";

        public static final String MIRROR_FAILED_OPERATIONS = GS_METRIC_TITLE + "MIRROR" + DELIMETER + "Failed_operations_count";
        public static final String MIRROR_WRITE_THROUGHPUT = GS_METRIC_TITLE + "MIRROR" + DELIMETER + "Mirror_Write_per_sec.";
        public static final String MIRROR_UPDATE_THROUGHPUT = GS_METRIC_TITLE + "MIRROR" + DELIMETER + "Mirror_Update_per_sec.";
        public static final String MIRROR_REMOVE_THROUGHPUT = GS_METRIC_TITLE + "MIRROR" + DELIMETER + "Mirror_Remove_per_sec.";




    }

}
