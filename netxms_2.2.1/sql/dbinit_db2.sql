CREATE TABLE metadata
(
  var_name varchar(63) not null,
  var_value varchar(255) not null,
  PRIMARY KEY(var_name)
) ;
CREATE TABLE config
(
  var_name varchar(63) not null,
  var_value varchar(2000) null,
  default_value varchar(2000) null,
  is_visible integer not null default 1,
  need_server_restart integer not null default 0,
  data_type char(1) not null default 'S',
  is_public char(1) not null default 'N',
  description varchar(450) null,
  possible_values LONG VARCHAR null,
  PRIMARY KEY(var_name)
) ;
CREATE TABLE config_clob
(
  var_name varchar(63) not null,
  var_value LONG VARCHAR null,
  PRIMARY KEY(var_name)
) ;
CREATE TABLE config_values
(
 var_name varchar(63) not null,
 var_value varchar(15) not null,
 var_description varchar(255) null,
 PRIMARY KEY(var_name,var_value)
) ;
CREATE TABLE users
(
  id integer not null,
  guid varchar(36) not null,
  name varchar(63) not null,
  password varchar(127) not null,
  system_access BIGINT not null,
  flags integer not null,
  full_name varchar(127) null,
  description varchar(255) null,
  grace_logins integer not null,
  auth_method integer not null,
  cert_mapping_method integer not null,
  cert_mapping_data LONG VARCHAR null,
  auth_failures integer not null,
  last_passwd_change integer not null,
  min_passwd_length integer not null,
  disabled_until integer not null,
  last_login integer not null,
  password_history LONG VARCHAR null,
  xmpp_id varchar(127) null,
  ldap_dn LONG VARCHAR null,
  ldap_unique_id varchar(64) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE user_groups
(
  id integer not null,
  guid varchar(36) not null,
  name varchar(63) not null,
  system_access BIGINT not null,
  flags integer not null,
  description varchar(255),
  ldap_dn LONG VARCHAR null,
  ldap_unique_id varchar(64) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE user_group_members
(
  group_id integer not null,
  user_id integer not null,
  PRIMARY KEY(group_id,user_id)
) ;
CREATE TABLE user_profiles
(
  user_id integer not null,
  var_name varchar(255) not null,
  var_value LONG VARCHAR not null,
  PRIMARY KEY(user_id,var_name)
) ;
CREATE TABLE userdb_custom_attributes
(
  object_id integer not null,
  attr_name varchar(255) not null,
  attr_value LONG VARCHAR not null,
  PRIMARY KEY(object_id,attr_name)
) ;
CREATE TABLE object_properties
(
  object_id integer not null,
  guid varchar(36) not null,
  name varchar(63) not null,
  status integer not null,
  is_deleted integer not null,
  is_system integer not null,
  last_modified integer not null,
  inherit_access_rights integer not null,
  status_calc_alg integer not null,
  status_prop_alg integer not null,
  status_fixed_val integer not null,
  status_shift integer not null,
  status_translation varchar(8) not null,
  status_single_threshold integer not null,
  status_thresholds varchar(8) not null,
  comments LONG VARCHAR null,
  location_type integer not null,
  latitude varchar(20),
  longitude varchar(20),
  location_accuracy integer not null,
  location_timestamp integer not null,
  image varchar(36) not null,
  submap_id integer not null,
  country varchar(63) null,
  city varchar(63) null,
  street_address varchar(255) null,
  postcode varchar(31) null,
  maint_mode char(1) not null,
  maint_event_id BIGINT not null,
  PRIMARY KEY(object_id)
) ;
CREATE TABLE object_urls
(
  object_id integer not null,
  url_id integer not null,
  url varchar(2000) null,
  description varchar(2000) null,
  PRIMARY KEY(object_id,url_id)
) ;
CREATE TABLE object_custom_attributes
(
  object_id integer not null,
  attr_name varchar(127) not null,
  attr_value LONG VARCHAR null,
  PRIMARY KEY(object_id,attr_name)
) ;
CREATE INDEX idx_ocattr_oid ON object_custom_attributes(object_id);
CREATE TABLE zones
(
  id integer not null,
  zone_guid integer not null,
  proxy_node integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE mobile_devices
(
  id integer not null,
  device_id varchar(64) not null,
  vendor varchar(64) null,
  model varchar(128) null,
  serial_number varchar(64) null,
  os_name varchar(32) null,
  os_version varchar(64) null,
  user_id varchar(64) null,
  battery_level integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE access_points
(
  id integer not null,
  node_id integer not null,
  mac_address varchar(12) null,
  vendor varchar(64) null,
  model varchar(128) null,
  serial_number varchar(64) null,
  ap_state integer not null,
  ap_index integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE racks
(
  id integer not null,
  height integer not null,
  top_bottom_num char(1) not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE chassis
(
  id integer not null,
  controller_id integer not null,
  flags integer not null,
  rack_id integer not null,
  rack_image varchar(36) null,
  rack_position integer not null,
  rack_height integer not null,
  rack_orientation integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE nodes
(
  id integer not null,
  primary_name varchar(255) null,
  primary_ip varchar(48) not null,
  tunnel_id varchar(36) null,
  node_flags integer not null,
  runtime_flags integer not null,
  snmp_version integer not null,
  snmp_port integer not null,
  community varchar(127) null,
  usm_auth_password varchar(127) null,
  usm_priv_password varchar(127) null,
  usm_methods integer not null,
  snmp_oid varchar(255) null,
  auth_method integer not null,
  secret varchar(64) null,
  agent_port integer not null,
  status_poll_type integer not null,
  agent_version varchar(63) null,
  platform_name varchar(63) null,
  poller_node_id integer not null,
  zone_guid integer not null,
  proxy_node integer not null,
  snmp_proxy integer not null,
  icmp_proxy integer not null,
  required_polls integer not null,
  uname varchar(255) null,
  use_ifxtable integer not null,
  snmp_sys_name varchar(127) null,
  snmp_sys_contact varchar(127) null,
  snmp_sys_location varchar(127) null,
  bridge_base_addr varchar(15) null,
  lldp_id varchar(63) null,
  down_since integer not null,
  boot_time integer not null,
  driver_name varchar(32) null,
  rack_id integer not null,
  rack_image varchar(36) null,
  rack_position integer not null,
  rack_height integer not null,
  rack_orientation integer not null,
  chassis_id integer not null,
  agent_cache_mode char(1) not null,
  last_agent_comm_time integer not null,
  syslog_msg_count BIGINT not null,
  snmp_trap_count BIGINT not null,
  node_type integer not null,
  node_subtype varchar(127) null,
  ssh_login varchar(63) null,
  ssh_password varchar(63) null,
  ssh_proxy integer not null,
  port_rows integer null,
  port_numbering_scheme integer null,
  agent_comp_mode char(1) not null,
  fail_time_snmp integer not null,
  fail_time_agent integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE clusters
(
  id integer not null,
  cluster_type integer not null,
  zone_guid integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE cluster_members
(
  cluster_id integer not null,
  node_id integer not null,
  PRIMARY KEY(cluster_id,node_id)
) ;
CREATE TABLE cluster_sync_subnets
(
  cluster_id integer not null,
  subnet_addr varchar(48) not null,
  subnet_mask integer not null,
  PRIMARY KEY(cluster_id,subnet_addr)
) ;
CREATE TABLE cluster_resources
(
  cluster_id integer not null,
  resource_id integer not null,
  resource_name varchar(255),
  ip_addr varchar(48) not null,
  current_owner integer not null,
  PRIMARY KEY(cluster_id,resource_id)
) ;
CREATE TABLE subnets
(
  id integer not null,
  ip_addr varchar(48) not null,
  ip_netmask integer not null,
  zone_guid integer not null,
  synthetic_mask integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE interfaces
(
  id integer not null,
  node_id integer not null,
  parent_iface integer not null,
  flags integer not null,
  if_type integer not null,
  if_index integer not null,
  mtu integer not null,
  speed BIGINT not null,
  bridge_port integer not null,
  phy_slot integer not null,
  phy_port integer not null,
  peer_node_id integer not null,
  peer_if_id integer not null,
  peer_proto integer not null,
  mac_addr varchar(12) not null,
  required_polls integer not null,
  admin_state integer not null,
  oper_state integer not null,
  dot1x_pae_state integer not null,
  dot1x_backend_state integer not null,
  description varchar(255) null,
  alias varchar(255) null,
  iftable_suffix varchar(127) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE interface_address_list
(
  iface_id integer not null,
  ip_addr varchar(48) not null,
  ip_netmask integer not null,
  PRIMARY KEY(iface_id,ip_addr)
) ;
CREATE TABLE network_services
(
  id integer not null,
  node_id integer not null,
  service_type integer not null,
  ip_bind_addr varchar(48) not null,
  ip_proto integer not null,
  ip_port integer not null,
  check_request LONG VARCHAR null,
  check_responce LONG VARCHAR null,
  poller_node_id integer not null,
  required_polls integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE vpn_connectors
(
  id integer not null,
  node_id integer not null,
  peer_gateway integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE vpn_connector_networks
(
  vpn_id integer not null,
  network_type integer not null,
  ip_addr varchar(48) not null,
  ip_netmask integer not null,
  PRIMARY KEY(vpn_id,ip_addr)
) ;
CREATE TABLE object_containers
(
  id integer not null,
  object_class integer not null,
  flags integer not null,
  auto_bind_filter LONG VARCHAR null,
  PRIMARY KEY(id)
) ;
CREATE TABLE conditions
(
  id integer not null,
  activation_event integer not null,
  deactivation_event integer not null,
  source_object integer not null,
  active_status integer not null,
  inactive_status integer not null,
  script LONG VARCHAR not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE cond_dci_map
(
  condition_id integer not null,
  sequence_number integer not null,
  dci_id integer not null,
  node_id integer not null,
  dci_func integer not null,
  num_polls integer not null,
  PRIMARY KEY(condition_id,sequence_number)
) ;
CREATE TABLE templates
(
  id integer not null,
  version integer not null,
  flags integer not null,
  apply_filter LONG VARCHAR null,
  PRIMARY KEY(id)
) ;
CREATE TABLE dct_node_map
(
  template_id integer not null,
  node_id integer not null,
  PRIMARY KEY(template_id,node_id)
) ;
CREATE TABLE nsmap
(
  subnet_id integer not null,
  node_id integer not null,
  PRIMARY KEY(subnet_id,node_id)
) ;
CREATE TABLE container_members
(
  container_id integer not null,
  object_id integer not null,
  PRIMARY KEY(container_id,object_id)
) ;
CREATE TABLE acl
(
  object_id integer not null,
  user_id integer not null,
  access_rights integer not null,
  PRIMARY KEY(object_id,user_id)
) ;
CREATE TABLE trusted_nodes
(
  source_object_id integer not null,
  target_node_id integer not null,
  PRIMARY KEY(source_object_id,target_node_id)
) ;
CREATE TABLE items
(
  item_id integer not null,
  node_id integer not null,
  template_id integer not null,
  template_item_id integer not null,
  guid varchar(36) not null,
  name varchar(1023) null,
  description varchar(255) null,
  flags integer not null,
  source integer not null,
  snmp_port integer not null,
  datatype integer not null,
  polling_interval integer not null,
  retention_time integer not null,
  status integer not null,
  snmp_raw_value_type integer not null,
  delta_calculation integer not null,
  transformation LONG VARCHAR,
  instance varchar(255) null,
  system_tag varchar(255) null,
  resource_id integer not null,
  proxy_node integer not null,
  base_units integer not null,
  unit_multiplier integer not null,
  custom_units_name varchar(63) null,
  perftab_settings LONG VARCHAR null,
  instd_method integer not null,
  instd_data varchar(255) null,
  instd_filter LONG VARCHAR null,
  samples integer not null,
  npe_name varchar(15) null,
  comments LONG VARCHAR null,
  instance_retention_time integer not null,
  PRIMARY KEY(item_id)
) ;
CREATE INDEX idx_items_node_id ON items(node_id);
CREATE TABLE thresholds
(
  threshold_id integer not null,
  item_id integer not null,
  sequence_number integer not null,
  fire_value varchar(255) null,
  rearm_value varchar(255) null,
  check_function integer not null,
  check_operation integer not null,
  sample_count integer not null,
  script LONG VARCHAR null,
  event_code integer not null,
  rearm_event_code integer not null,
  repeat_interval integer not null,
  current_state integer not null,
  current_severity integer not null,
  match_count integer not null,
  last_event_timestamp integer not null,
  PRIMARY KEY(threshold_id)
) ;
CREATE INDEX idx_thresholds_item_id ON thresholds(item_id);
CREATE INDEX idx_thresholds_sequence ON thresholds(sequence_number);
CREATE TABLE dc_tables
(
  item_id integer not null,
  node_id integer not null,
  template_id integer not null,
  template_item_id integer not null,
  guid varchar(36) not null,
  name varchar(1023) null,
  description varchar(255) null,
  flags integer not null,
  source integer not null,
  snmp_port integer not null,
  polling_interval integer not null,
  retention_time integer not null,
  status integer not null,
  system_tag varchar(255) null,
  resource_id integer not null,
  proxy_node integer not null,
  perftab_settings LONG VARCHAR null,
  transformation_script LONG VARCHAR null,
  comments LONG VARCHAR null,
  instance varchar(255) null,
  instd_method integer not null,
  instd_data varchar(255) null,
  instd_filter LONG VARCHAR null,
  instance_retention_time integer not null,
  PRIMARY KEY(item_id)
) ;
CREATE INDEX idx_dc_tables_node_id ON dc_tables(node_id);
CREATE TABLE dc_table_columns
(
  table_id integer not null,
  sequence_number integer not null,
  column_name varchar(63) not null,
  snmp_oid varchar(1023) null,
  flags integer not null,
  display_name varchar(255) null,
  PRIMARY KEY(table_id,column_name)
) ;
CREATE TABLE dct_column_names
(
  column_id integer not null,
  column_name varchar(63) not null,
  PRIMARY KEY(column_id)
) ;
CREATE TABLE dct_thresholds
(
  id integer not null,
  table_id integer not null,
  sequence_number integer not null,
  activation_event integer not null,
  deactivation_event integer not null,
  sample_count integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE dct_threshold_conditions
(
  threshold_id integer not null,
  group_id integer not null,
  sequence_number integer not null,
  column_name varchar(63) null,
  check_operation integer not null,
  check_value varchar(255) null,
  PRIMARY KEY(threshold_id,group_id,sequence_number)
) ;
CREATE TABLE dct_threshold_instances
(
  threshold_id integer not null,
  instance varchar(255) not null,
  match_count integer not null,
  is_active char(1) not null,
  PRIMARY KEY(threshold_id,instance)
) ;
CREATE TABLE dci_schedules
(
  item_id integer not null,
  schedule_id integer not null,
  schedule varchar(255) null,
  PRIMARY KEY(item_id,schedule_id)
) ;
CREATE TABLE raw_dci_values
(
  item_id integer not null,
  raw_value varchar(255) null,
  transformed_value varchar(255) null,
  last_poll_time integer not null,
  PRIMARY KEY(item_id)
) ;
CREATE TABLE dci_access
(
   dci_id integer not null,
   user_id integer not null,
   PRIMARY KEY(dci_id,user_id)
) ;
CREATE TABLE event_cfg
(
  event_code integer not null,
  event_name varchar(63) not null,
  guid varchar(36) not null,
  severity integer not null,
  flags integer not null,
  message varchar(2000) null,
  description LONG VARCHAR null,
  PRIMARY KEY(event_code)
) ;
CREATE TABLE event_log
(
  event_id BIGINT not null,
  event_code integer not null,
  event_timestamp integer not null,
  event_source integer not null,
  dci_id integer not null,
  event_severity integer not null,
  event_message varchar(2000) null,
  root_event_id BIGINT not null,
  user_tag varchar(63) null,
  PRIMARY KEY(event_id)
) ;
CREATE INDEX idx_event_log_event_timestamp ON event_log(event_timestamp);
CREATE INDEX idx_event_log_source ON event_log(event_source);
CREATE INDEX idx_event_log_root_id ON event_log(root_event_id);
CREATE TABLE actions
(
  action_id integer not null,
  action_name varchar(63) not null,
  action_type integer not null,
  is_disabled integer not null,
  rcpt_addr varchar(255) null,
  email_subject varchar(255) null,
  action_data LONG VARCHAR null,
  PRIMARY KEY(action_id)
) ;
CREATE TABLE event_groups
(
  id integer not null,
  name varchar(63) not null,
  description varchar(255) not null,
  range_start integer not null,
  range_end integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE event_group_members
(
  group_id integer not null,
  event_code integer not null,
  PRIMARY KEY(group_id,event_code)
) ;
CREATE TABLE event_policy
(
  rule_id integer not null,
  rule_guid varchar(36) not null,
  flags integer not null,
  comments LONG VARCHAR null,
  script LONG VARCHAR null,
  alarm_message varchar(2000) null,
  alarm_severity integer not null,
  alarm_key varchar(255) null,
  alarm_timeout integer not null,
  alarm_timeout_event integer not null,
  PRIMARY KEY(rule_id)
) ;
CREATE TABLE policy_source_list
(
  rule_id integer not null,
  object_id integer not null,
  PRIMARY KEY(rule_id,object_id)
) ;
CREATE TABLE policy_event_list
(
  rule_id integer not null,
  event_code integer not null,
  PRIMARY KEY(rule_id,event_code)
) ;
CREATE TABLE policy_action_list
(
  rule_id integer not null,
  action_id integer not null,
  PRIMARY KEY(rule_id,action_id)
) ;
CREATE TABLE policy_pstorage_actions
(
  rule_id integer not null,
  ps_key varchar(255) not null,
  value varchar(2000) null,
  action integer not null,
  PRIMARY KEY(rule_id,ps_key,action)
) ;
CREATE TABLE alarms
(
  alarm_id integer not null,
  alarm_state integer not null,
  hd_state integer not null,
  hd_ref varchar(63) null,
  creation_time integer not null,
  last_change_time integer not null,
  source_object_id integer not null,
  source_event_code integer not null,
  source_event_id BIGINT not null,
  dci_id integer not null,
  message varchar(2000) null,
  original_severity integer not null,
  current_severity integer not null,
  repeat_count integer not null,
  alarm_key varchar(255) null,
  ack_by integer not null,
  resolved_by integer not null,
  term_by integer not null,
  timeout integer not null,
  timeout_event integer not null,
  ack_timeout integer not null,
  alarm_category_ids varchar(255) null,
  PRIMARY KEY(alarm_id)
) ;
CREATE TABLE alarm_notes
(
  note_id integer not null,
  alarm_id integer not null,
  change_time integer not null,
  user_id integer not null,
  note_text LONG VARCHAR null,
  PRIMARY KEY(note_id)
) ;
CREATE INDEX idx_alarm_notes_alarm_id ON alarm_notes(alarm_id);
CREATE TABLE alarm_events
(
  alarm_id integer not null,
  event_id BIGINT not null,
  event_code integer not null,
  event_name varchar(63) null,
  severity integer not null,
  source_object_id integer not null,
  event_timestamp integer not null,
  message varchar(2000) null,
  PRIMARY KEY(alarm_id,event_id)
) ;
CREATE INDEX idx_alarm_events_alarm_id ON alarm_events(alarm_id);
CREATE TABLE alarm_categories
(
  id integer not null,
  name varchar(63) null,
  descr varchar(255) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE alarm_category_acl
(
  category_id integer not null,
  user_id integer not null,
  PRIMARY KEY(category_id,user_id)
) ;
CREATE TABLE alarm_category_map
(
  alarm_id integer not null,
  category_id integer not null,
  PRIMARY KEY(alarm_id,category_id)
) ;
CREATE TABLE snmp_trap_cfg
(
  guid varchar(36) not null,
  trap_id integer not null,
  snmp_oid varchar(255),
  event_code integer not null,
  user_tag varchar(63),
  description varchar(255),
  PRIMARY KEY(trap_id)
) ;
CREATE TABLE snmp_trap_pmap
(
  trap_id integer not null,
  parameter integer not null,
  flags integer not null,
  snmp_oid varchar(255) null,
  description varchar(255) null,
  PRIMARY KEY(trap_id,parameter)
) ;
CREATE TABLE agent_pkg
(
  pkg_id integer not null,
  pkg_name varchar(63),
  version varchar(31),
  platform varchar(63),
  pkg_file varchar(255),
  description varchar(255),
  PRIMARY KEY(pkg_id)
) ;
CREATE TABLE object_tools
(
  tool_id integer not null,
  guid varchar(36) not null,
  tool_name varchar(255) null,
  tool_type integer not null,
  tool_data LONG VARCHAR null,
  description varchar(255) null,
  flags integer not null,
  tool_filter LONG VARCHAR null,
  confirmation_text varchar(255) null,
  command_name varchar(255) null,
  command_short_name varchar(31) null,
  icon LONG VARCHAR null,
  PRIMARY KEY(tool_id)
) ;
CREATE TABLE object_tools_acl
(
  tool_id integer not null,
  user_id integer not null,
  PRIMARY KEY(tool_id,user_id)
) ;
CREATE TABLE object_tools_table_columns
(
  tool_id integer not null,
  col_number integer not null,
  col_name varchar(255) null,
  col_oid varchar(255) null,
  col_format integer,
  col_substr integer,
  PRIMARY KEY(tool_id,col_number)
) ;
CREATE TABLE object_tools_input_fields
(
  tool_id integer not null,
  name varchar(31) not null,
  input_type char(1) not null,
  display_name varchar(127) null,
  sequence_num integer not null,
  config LONG VARCHAR null,
  PRIMARY KEY(tool_id,name)
) ;
CREATE TABLE syslog
(
  msg_id BIGINT not null,
  msg_timestamp integer not null,
  facility integer not null,
  severity integer not null,
  source_object_id integer not null,
  hostname varchar(127) null,
  msg_tag varchar(32) null,
  msg_text LONG VARCHAR null,
  PRIMARY KEY(msg_id)
) ;
CREATE INDEX idx_syslog_msg_timestamp ON syslog(msg_timestamp);
CREATE INDEX idx_syslog_source ON syslog(source_object_id);
CREATE TABLE script_library
(
  guid varchar(36) not null,
  script_id integer not null,
  script_name varchar(255) not null,
  script_code LONG VARCHAR null,
  PRIMARY KEY(script_id)
) ;
CREATE TABLE snmp_trap_log
(
  trap_id BIGINT not null,
  trap_timestamp integer not null,
  ip_addr varchar(48) not null,
  object_id integer not null,
  trap_oid varchar(255) not null,
  trap_varlist LONG VARCHAR null,
  PRIMARY KEY(trap_id)
) ;
CREATE INDEX idx_snmp_trap_log_tt ON snmp_trap_log(trap_timestamp);
CREATE INDEX idx_snmp_trap_log_oid ON snmp_trap_log(object_id);
CREATE TABLE agent_configs
(
  config_id integer not null,
  config_name varchar(255) not null,
  config_file LONG VARCHAR not null,
  config_filter LONG VARCHAR not null,
  sequence_number integer not null,
  PRIMARY KEY(config_id)
) ;
CREATE TABLE address_lists
(
  list_type integer not null,
  community_id integer not null,
  addr_type integer not null,
  addr1 varchar(48) not null,
  addr2 varchar(48) not null,
  PRIMARY KEY(list_type,community_id,addr_type,addr1,addr2)
) ;
CREATE INDEX idx_address_lists_list_type ON address_lists(list_type);
CREATE TABLE graphs
(
  graph_id integer not null,
  owner_id integer not null,
  flags integer not null,
  name varchar(255) not null,
  config LONG VARCHAR null,
  filters LONG VARCHAR null,
  PRIMARY KEY(graph_id)
) ;
CREATE TABLE graph_acl
(
  graph_id integer not null,
  user_id integer not null,
  user_rights integer not null,
  PRIMARY KEY(graph_id,user_id)
) ;
CREATE TABLE certificates
(
  cert_id integer not null,
  cert_type integer not null,
  cert_data LONG VARCHAR not null,
  subject LONG VARCHAR not null,
  comments LONG VARCHAR not null,
  PRIMARY KEY(cert_id)
) ;
CREATE TABLE audit_log
(
  record_id integer not null,
  timestamp integer not null,
  subsystem varchar(32) not null,
  success integer not null,
  user_id integer not null,
  workstation varchar(63) not null,
  session_id integer not null,
  object_id integer not null,
  message LONG VARCHAR null,
  old_value LONG VARCHAR null,
  new_value LONG VARCHAR null,
  value_diff LONG VARCHAR null,
  PRIMARY KEY(record_id)
) ;
CREATE TABLE persistent_storage
(
  entry_key varchar(255) not null,
  value varchar(2000) null,
  PRIMARY KEY(entry_key)
) ;
CREATE TABLE snmp_communities
(
  id integer not null,
  community varchar(255) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE ap_common
(
  id integer not null,
  policy_type integer not null,
  version integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE ap_bindings
(
  policy_id integer not null,
  node_id integer not null,
  PRIMARY KEY(policy_id,node_id)
) ;
CREATE TABLE ap_config_files
(
  policy_id integer not null,
  file_content LONG VARCHAR null,
  PRIMARY KEY(policy_id)
) ;
CREATE TABLE ap_log_parser
(
  policy_id integer not null,
  file_content LONG VARCHAR null,
  PRIMARY KEY(policy_id)
) ;
CREATE TABLE usm_credentials
(
  id integer not null,
  user_name varchar(255) not null,
  auth_method integer not null,
  priv_method integer not null,
  auth_password varchar(255),
  priv_password varchar(255),
  PRIMARY KEY(id)
) ;
CREATE TABLE network_maps
(
  id integer not null,
  map_type integer not null,
  layout integer not null,
  radius integer not null,
  background varchar(36) null,
  bg_latitude varchar(20) null,
  bg_longitude varchar(20) null,
  bg_zoom integer null,
  flags integer not null,
  bg_color integer not null,
  link_color integer not null,
  link_routing integer not null,
  object_display_mode integer not null,
  filter LONG VARCHAR null,
  PRIMARY KEY(id)
) ;
CREATE TABLE network_map_elements
(
  map_id integer not null,
  element_id integer not null,
  element_type integer not null,
  element_data LONG VARCHAR not null,
  flags integer not null,
  PRIMARY KEY(map_id,element_id)
) ;
CREATE TABLE network_map_links
(
  map_id integer not null,
  element1 integer not null,
  element2 integer not null,
  link_type integer not null,
  link_name varchar(255) null,
  connector_name1 varchar(255) null,
  connector_name2 varchar(255) null,
  element_data LONG VARCHAR null,
  flags integer not null
) ;
CREATE INDEX idx_network_map_links_map_id ON network_map_links(map_id);
CREATE TABLE network_map_seed_nodes
(
  map_id integer not null,
  seed_node_id integer not null,
  PRIMARY KEY(map_id,seed_node_id)
) ;
CREATE TABLE images
(
  guid varchar(36) not null,
  name varchar(63) not null,
  category varchar(63) not null,
  mimetype varchar(32) not null,
  protected integer default 0,
  PRIMARY KEY(guid),
  UNIQUE(name, category)
) ;
CREATE TABLE dashboards
(
  id integer not null,
  num_columns integer not null,
  options integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE dashboard_elements
(
  dashboard_id integer not null,
  element_id integer not null,
  element_type integer not null,
  element_data LONG VARCHAR null,
  layout_data LONG VARCHAR null,
  PRIMARY KEY(dashboard_id,element_id)
) ;
CREATE TABLE dashboard_associations
(
  object_id integer not null,
  dashboard_id integer not null,
  PRIMARY KEY(object_id,dashboard_id)
) ;
CREATE TABLE business_services
(
  service_id integer not null,
  PRIMARY KEY(service_id)
) ;
CREATE TABLE slm_checks
(
  id integer not null,
  type integer not null,
  content LONG VARCHAR null,
  threshold_id integer not null,
  reason varchar(255) null,
  is_template integer not null,
  template_id integer not null,
  current_ticket integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE node_links
(
  nodelink_id integer not null,
  node_id integer not null,
  PRIMARY KEY(nodelink_id)
) ;
CREATE TABLE slm_agreements
(
  agreement_id integer not null,
  service_id integer not null,
  org_id integer not null,
  uptime varchar(63) not null,
  period integer not null,
  start_date integer not null,
  notes varchar(255),
  PRIMARY KEY(agreement_id)
) ;
CREATE TABLE slm_tickets
(
  ticket_id integer not null,
  service_id integer not null,
  check_id integer not null,
  create_timestamp integer not null,
  close_timestamp integer not null,
  reason varchar(255) null,
  PRIMARY KEY(ticket_id)
) ;
CREATE TABLE slm_service_history
(
  record_id integer not null,
  service_id integer not null,
  change_timestamp integer not null,
  new_status integer not null,
  PRIMARY KEY(record_id)
) ;
CREATE TABLE organizations
(
  id integer not null,
  parent_id integer not null,
  org_type integer not null,
  name varchar(63) not null,
  description varchar(255),
  manager integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE persons
(
  id integer not null,
  org_id integer not null,
  first_name varchar(63),
  last_name varchar(63),
  title varchar(255),
  status integer not null,
  PRIMARY KEY(id)
) ;
CREATE TABLE job_history
(
  id integer not null,
  time_created integer not null,
  time_started integer not null,
  time_finished integer not null,
  job_type varchar(127) null,
  description varchar(255) null,
  additional_info varchar(255) null,
  node_id integer not null,
  user_id integer not null,
  status integer not null,
  failure_message varchar(255) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE licenses
(
  id integer not null,
  content LONG VARCHAR null,
  PRIMARY KEY(id)
) ;
CREATE TABLE mapping_tables
(
  id integer not null,
  name varchar(63) not null,
  flags integer not null,
  description varchar(4000) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE mapping_data
(
  table_id integer not null,
  md_key varchar(63) not null,
  md_value varchar(255) null,
  description varchar(4000) null,
  PRIMARY KEY(table_id,md_key)
) ;
CREATE TABLE dci_summary_tables
(
  id integer not null,
  guid varchar(36) not null,
  menu_path varchar(255) null,
  title varchar(127) null,
  node_filter LONG VARCHAR null,
  flags integer not null,
  columns LONG VARCHAR null,
  table_dci_name varchar(255) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE scheduled_tasks
(
  id integer not null,
  taskId varchar(255) null,
  schedule varchar(127) null,
  params varchar(1023) null,
  execution_time integer not null,
  last_execution_time integer not null,
  flags integer not null,
  owner integer not null,
  object_id integer not null,
  comments varchar(255) null,
  PRIMARY KEY(id)
) ;
CREATE TABLE zmq_subscription
(
  object_id integer not null,
  subscription_type char(1) not null,
  ignore_items integer not null,
  items LONG VARCHAR,
  PRIMARY KEY(object_id, subscription_type)
) ;
CREATE TABLE currency_codes
(
 numeric_code char(3) not null,
 alpha_code char(3) not null,
 description varchar(127) not null,
 exponent integer not null,
 PRIMARY KEY(numeric_code)
) ;
CREATE TABLE country_codes
(
 numeric_code char(3) not null,
 alpha_code char(2) not null,
 alpha3_code char(3) not null,
 name varchar(127) not null,
 PRIMARY KEY(numeric_code)
) ;
CREATE TABLE config_repositories
(
 id integer not null,
 url varchar(1023) not null,
 auth_token varchar(63) null,
 description varchar(1023) null,
 PRIMARY KEY(id)
) ;
CREATE TABLE port_layouts
(
   device_oid varchar(255) not null,
   numbering_scheme char(1) not null,
   row_count char(1) not null,
   layout_data varchar(4000) null,
   PRIMARY KEY(device_oid)
) ;
CREATE TABLE object_access_snapshot
(
   user_id integer not null,
   object_id integer not null,
   access_rights integer not null,
   PRIMARY KEY(user_id,object_id)
) ;
INSERT INTO metadata (var_name,var_value) VALUES ('SchemaVersion',700);
INSERT INTO metadata (var_name,var_value) VALUES ('SchemaVersionMajor',22);
INSERT INTO metadata (var_name,var_value) VALUES ('SchemaVersionMinor',5);
INSERT INTO metadata (var_name,var_value) VALUES ('Syntax','DB2');
INSERT INTO metadata (var_name,var_value)
 VALUES ('IDataTableCreationCommand','CREATE TABLE idata_%d (item_id integer not null,idata_timestamp integer not null,idata_value varchar(255) null)');
INSERT INTO metadata (var_name,var_value)
 VALUES ('IDataIndexCreationCommand_0','CREATE INDEX idx_idata_%d_id_timestamp ON idata_%d(item_id,idata_timestamp DESC)');
INSERT INTO metadata (var_name,var_value)
 VALUES ('TDataTableCreationCommand_0','CREATE TABLE tdata_%d (item_id integer not null,tdata_timestamp integer not null,tdata_value ' || 'LONG VARCHAR' || ' null)');
INSERT INTO metadata (var_name,var_value)
 VALUES ('TDataIndexCreationCommand_0','CREATE INDEX idx_tdata_%d ON tdata_%d(item_id,tdata_timestamp)');
INSERT INTO metadata (var_name,var_value)
 VALUES ('LocationHistory','CREATE TABLE gps_history_%d (latitude varchar(20), longitude varchar(20), accuracy integer not null, start_timestamp integer not null, end_timestamp integer not null, PRIMARY KEY(start_timestamp))');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ActiveDiscoveryInterval','7200','7200',1,1,'I','Interval in seconds between active network discovery polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ActiveNetworkDiscovery','0','0',1,1,'B','Enable/disable active network discovery. This setting is change by Network Discovery GUI');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AgentCommandTimeout','4000','4000',1,1,'I','Timeout in milliseconds for commands sent to agent. If agent did not respond to command within given number of seconds, \ncommand considered as failed.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AgentDefaultSharedSecret','netxms','netxms',1,0,'S','String that will be used as a shared secret in case if agent will required authentication.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AgentUpgradeWaitTime','600','600',1,0,'I','Maximum wait time in seconds for agent restart after upgrade. If agent cannot be contacted after this time period, \nupgrade process is considered as failed.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AlarmHistoryRetentionTime','180','180',1,0,'I','A number of days the server keeps an alarm history in the database.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AlarmListDisplayLimit','4096','4096',1,0,'I','Maximum alarm count that will be displayed on Alarm Browser page. Alarms that exceed this count will not be shown.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AlarmSummaryEmailRecipients','','',1,0,'S','A semicolon separated list of alarm summary e-mail recipient addresses');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AlarmSummaryEmailSchedule','0 0 * * *','0 0 * * *',1,0,'S','Schedule for sending alarm summary e-mails in cron format');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AllowDirectSMS','0','0',1,0,'B','Allow/disallow sending of SMS via NetXMS server using nxsms utility.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AllowedCiphers','63','63',1,1,'I','A bitmask for encryption algorithms allowed in the server(sum the values to allow multiple algorithms at once): \n\t*1 - AES256 \n\t*2 - Blowfish-256 \n\t*4 - IDEA \n\t*8 - 3DES\n\t*16 - AES128\n\t*32 - Blowfish-128');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AllowTrapVarbindsConversion','1','1',1,1,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AnonymousFileAccess','0','0',1,0,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ApplyDCIFromTemplateToDisabledDCI','1','1',1,1,'B','Enable applying all DCIs from a template to the node, including disabled ones.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('AuditLogRetentionTime','90','90',1,0,'I','Retention time in days for the records in audit log. All records older than specified will be deleted by housekeeping process.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('BeaconHosts','','',1,1,'S','Comma-separated list of hosts to be used as beacons for checking NetXMS server network connectivity. Either DNS names \nor IP addresses can be used. This list is pinged by NetXMS server and if none of the hosts have responded, server considers that connection \nwith network is lost and generates specific event.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('BeaconPollingInterval','1000','1000',1,1,'I','Interval in milliseconds between beacon hosts polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('BeaconTimeout','1000','1000',1,1,'I','Timeout in milliseconds to consider beacon host unreachable.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('BlockInactiveUserAccounts','0','0',1,0,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('CapabilityExpirationTime','604800','604800',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('CaseInsensitiveLoginNames','0','0',1,1,'B','Enable/disable case insensitive login names');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('CheckTrustedNodes','0','0',1,1,'B','Enable/disable trusted nodes check');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ClientListenerPort','4701','4701',1,1,'I','The server port for incoming client connections (such as management console).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ClusterContainerAutoBind','0','0',1,0,'B','Enable/disable container auto binding for clusters.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ClusterTemplateAutoApply','0','0',1,0,'B','Enable/disable template auto apply for clusters.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ConditionPollingInterval','60','60',1,1,'I','Interval in seconds between polling (re-evaluating) of condition objects.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ConfigurationPollingInterval','3600','3600',1,1,'I','Interval in seconds between configuration polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,is_public,description) VALUES ('DashboardDataExportEnableInterpolation','1','1',1,1,'B','Y','Enable/disable data interpolation in dashboard data export.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DataCollector.ThreadPool.BaseSize','10','10',1,1,'I','Base size for data collector thread pool.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DataCollector.ThreadPool.MaxSize','250','250',1,1,'I','Maximum size for data collector thread pool.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBConnectionPoolBaseSize','10','10',1,1,'I','A number of connections to the database created on the server startup.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBConnectionPoolCooldownTime','300','300',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBConnectionPoolMaxLifetime','14400','14400',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBConnectionPoolMaxSize','30','30',1,1,'I','A maximum number of connections in the connection pool.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBLockInfo','','',0,0,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBLockPID','0','0',0,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBLockStatus','UNLOCKED','UNLOCKED',0,1,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DBWriter.MaxRecordsPerTransaction','1000','1000',1,1,'I','Maximum number of records per one transaction for delayed database writes');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultAgentCacheMode','2','2',1,1,'C','Default agent cache mode');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultAgentProtocolCompressionMode','1','1',1,0,'C','Default agent protocol compression mode');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultConsoleDateFormat','dd.MM.yyyy','dd.MM.yyyy',1,0,'S','Default date display format for GUI.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultConsoleShortTimeFormat','HH:mm','HH:mm',1,0,'S','Default short time display format for GUI.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultConsoleTimeFormat','HH:mm:ss','HH:mm:ss',1,0,'S','Default long time display format for GUI.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultDCIPollingInterval','60','60',1,0,'I','Default polling interval for newly created DCI (in seconds).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultDCIRetentionTime','30','30',1,0,'I','Default retention time for newly created DCI (in days).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultEncryptionPolicy','1','1',1,1,'C','Set the default encryption policy for communications with agents.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultInterfaceExpectedState','1','1',1,0,'C','Default expected state for new interface objects.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultMapBackgroundColor','0xffffff','0xffffff',1,0,'H','Default background color for new network map objects.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultSubnetMaskIPv4','24','24',1,0,'I','Default mask for synthetic IPv4 subnets.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DefaultSubnetMaskIPv6','64','64',1,0,'I','Default mask for synthetic IPv6 subnets.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DeleteAlarmsOfDeletedObject','1','1',1,0,'B','Enable/disable automatic alarm removal of an object when it is deleted.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DeleteEmptySubnets','0','0',1,1,'B','Enable/disable automatic deletion of subnet objects without any nodes within.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DeleteEventsOfDeletedObject','1','1',1,0,'B','Enable/disable automatic event removal of an object when it is deleted.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DeleteUnreachableNodesPeriod','0','0',1,0,'I','Delete nodes which were unreachable for a number of days specified by this parameter.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DiscoveryFilter','none','none',1,0,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DiscoveryFilterFlags','0','0',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('DiscoveryPollingInterval','900','900',1,1,'I','Interval in seconds between passive network discovery polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableAgentRegistration','1','1',1,0,'B','Enable/disable agent self-registration');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableAuditLog','1','1',1,1,'B','Enable/disable audit log.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableCheckPointSNMP','0','0',1,0,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableReportingServer','0','0',1,1,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableEventStormDetection','0','0',1,1,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableISCListener','0','0',1,1,'B','Enable/disable Inter-Server Communications Listener.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableNXSLContainerFunctions','1','1',1,1,'B','Enable/disable server-side NXSL functions for container management (such as CreateContainer, RemoveContainer, BindObject, \nUnbindObject).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableObjectTransactions','0','0',1,1,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableSNMPTraps','1','1',1,1,'B','Enable/disable SNMP trap processing.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableAlarmSummaryEmails','0','0',1,0,'B','Enable/disable alarm summary e-mails.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableSyslogReceiver','0','0',1,1,'B','Enable/disable receiving of syslog messages.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableTimedAlarmAck','1','1',1,1,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableXMPPConnector','0','0',1,1,'B','Enable/disable XMPP connector (required to enable XMPP message sending).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EnableZoning','0','0',1,1,'B','Enable/disable zoning support');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EscapeLocalCommands','0','0',1,0,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EventLogRetentionTime','90','90',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EventStormDuration','15','15',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('EventStormEventsPerSecond','100','100',1,1,'I','Event storm events per second');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ExtendedLogQueryAccessControl','0','0',1,0,'B','Enable/disable extended access control in log queries.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ExternalAuditFacility','13','13',1,1,'I','Syslog facility to be used in audit log records sent to external server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ExternalAuditPort','514','514',1,1,'I','UDP port of external syslog server to send audit records to.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ExternalAuditServer','none','none',1,1,'S','External syslog server to send audit records to. If set to "none", external audit logging is disabled.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ExternalAuditSeverity','5','5',1,1,'I','Syslog severity to be used in audit log records sent to external server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ExternalAuditTag','netxmsd-audit','netxmsd-audit',1,1,'S','Syslog tag to be used in audit log records sent to external server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('FirstFreeObjectId','100','100',0,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('FixedStatusValue','0','0',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('GraceLoginCount','5','5',1,0,'I','User grace login count');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('HelpDeskLink','none','none',1,1,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('HousekeeperStartTime','02:00','02:00',1,1,'S','Timer when housekeeper starts. Housekeeper deletes old log lines, old DCI data, cleans removed objects and does VACUUM for \nPostgreSQL.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('IcmpPingSize','46','46',1,1,'I','Size of ICMP packets (in bytes, excluding IP header size) used for status polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('IcmpPingTimeout','1500','1500',1,1,'I','Timeout for ICMP ping used for status polls (in milliseconds).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ImportConfigurationOnStartup','1','1',1,1,'B','Import configuration from local files on server startup.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('InstancePollingInterval','600','600',1,1,'I','Instance polling interval (in seconds).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('InstanceRetentionTime','0','0',1,1,'I','Default retention time (in days) for missing DCI instances');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('InternalCA','0','0',1,1,'B','Enable/disable internal certificate authority.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('IntruderLockoutThreshold','0','0',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('IntruderLockoutTime','30','30',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JiraIssueType','Task','Task',1,0,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JiraLogin','netxms','netxms',1,1,'S','Jira login name.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JiraPassword','','',1,1,'S','Jira password');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JiraProjectCode','NETXMS','NETXMS',1,0,'S','Jira project code');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JiraProjectComponent','','',1,0,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JiraServerURL','http://localhost','http://localhost',1,1,'S','The URL of the Jira server');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JobHistoryRetentionTime','90','90',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('JobRetryCount','5','5',1,0,'I','Maximum mumber of job execution retries.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('KeepAliveInterval','60','60',1,1,'I','Interval in seconds between sending keep alive packets to connected clients.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapGroupClass','','',1,0,'S','Specifies which object class represents group objects. If the found entry is not of user or group class, it will be ignored.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapGroupUniqueId','','',1,0,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapConnectionString','ldap://localhost:389','ldap://localhost:389',1,0,'S','The LdapConnectionString configuration parameter may be a comma- or whitespace-separated list of URIs containing only the \nschema, the host, and the port fields. Format: schema://host:port.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapMappingDescription','','',1,0,'S','The name of an attribute whose value will be used as a user''s description.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapMappingFullName','displayName','displayName',1,0,'S','The name of an attribute whose value will be used as a user''s full name.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapMappingName','','',1,0,'S','The name of an attribute whose value will be used as a user''s login name.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapPageSize','1000','1000',1,0,'I','The maximum amount of records that can be returned in one search page.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapSearchBase','','',1,0,'S','The DN of the entry at which to start the search.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapSearchFilter','','',1,0,'S','A string representation of the filter to apply in the search.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapSyncInterval','0','0',1,0,'I','The synchronization interval (in minutes) between the NetXMS server and the LDAP server. If the parameter is set to 0, no \nsynchronization will take place.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapSyncUser','','',1,0,'S','User login for LDAP synchronization.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapSyncUserPassword','','',1,0,'S', 'User password for LDAP synchronization.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapUserClass','','',1,0,'S','The object class which represents user objects. If the found entry is not of user or group class, it will be ignored.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapUserDeleteAction','1','1',1,0,'C','This parameter specifies what should be done while synchronizing with a deleted user/group from LDAP.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LdapUserUniqueId','','',1,0,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LockTimeout','60000','60000',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LogAllSNMPTraps','0','0',1,1,'B','Log all SNMP traps.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('LongRunningQueryThreshold','0','0',1,1,'I','Threshold in milliseconds to report long running SQL queries (0 to disable)');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('MailEncoding','utf8','utf8',1,0,'S','Encoding for e-mails generated by NetXMS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,data_type,need_server_restart,description) VALUES ('MessageOfTheDay','','',1,'S',0,'Message to be shown when a user logs into the console.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('MinPasswordLength','0','0',1,0,'I','Default minimum password length for a NetXMS user. The default applied only if per-user setting is not defined.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('MinViewRefreshInterval','1000','1000',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('MobileDeviceListenerPort','4747','4747',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('NumberOfUpgradeThreads','10','10',1,0,'I','The number of threads used to perform agent upgrades (i.e. maximum number of parallel upgrades).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('OfflineDataRelevanceTime','86400','86400',1,1,'I','Time period in seconds within which received offline data still relevant for threshold validation.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('PasswordComplexity','0','0',1,0,'I','Set of flags to enforce password complexity.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('PasswordExpiration','0','0',1,0,'I','Password expiration time in days. If set to 0, password expiration is disabled.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('PasswordHistoryLength','0','0',1,0,'I','Number of previous passwords to keep. Users are not allowed to set password if it matches one from previous passwords list.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('PollCountForStatusChange','1','1',1,1,'I','The number of consecutive unsuccessful polls required to declare interface as down.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('PollerThreadPoolBaseSize','10','10',1,1,'I','The base thread pool size.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('PollerThreadPoolMaxSize','250','250',1,1,'I','Maximum thread pool size.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSAuthMethod','PAP','PAP',1,0,'S','RADIUS authentication method to be used (PAP, CHAP, MS-CHAPv1, MS-CHAPv2).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSNumRetries','5','5',1,0,'I','The number of retries for RADIUS authentication.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSPort','1645','1645',1,0,'I','Port number used for connection to primary RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSSecondaryPort','1645','1645',1,0,'I','Port number used for connection to secondary RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSSecondarySecret','netxms','netxms',1,0,'S','Shared secret used for communication with secondary RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSSecondaryServer','none','none',1,0,'S','Host name or IP address of secondary RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSSecret','netxms','netxms',1,0,'S','Shared secret used for communication with primary RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSServer','none','none',1,0,'S','Host name or IP address of primary RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RADIUSTimeout','3','3',1,0,'I','Timeout in seconds for requests to RADIUS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ReceiveForwardedEvents','0','0',1,0,'B','Enable/disable reception of events forwarded by another NetXMS server. Please note that for external event reception ISC listener \nshould be enabled as well.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ReportingServerHostname','localhost','localhost',1,1,'S','The hostname of the reporting server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ReportingServerPort','4710','4710',1,1,'I','The port of the reporting server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ResolveDNSToIPOnStatusPoll','0','0',1,1,'B','Resolve DNS to IP on status poll.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ResolveNodeNames','1','1',1,0,'B','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RoutingTableUpdateInterval','300','300',1,1,'I','Interval in seconds between reading routing table from node.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('RunNetworkDiscovery','0','0',1,1,'B','Enable/disable automatic network discovery process. *This setting is change by Network Discovery GUI*.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ServerColor','','',1,0,'H','Identification color for this server');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ServerCommandOutputTimeout','60','60',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ServerName','','',1,0,'S','Name of this server');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMSDriver','<none>','<none>',1,1,'S','Mobile phone driver to be used for sending SMS.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMSDrvConfig','','',1,1,'S','SMS driver parameters. For "generic" driver, it should be the name of COM port device.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SNMPPorts','161','161',1,0,'S','Comma separated list of UDP ports used by SNMP capable devices.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SNMPRequestTimeout','1500','1500',1,1,'I','Timeout in milliseconds for SNMP requests sent by NetXMS server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SNMPTrapLogRetentionTime','90','90',1,0,'I','The time how long SNMP trap logs are retained.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SNMPTrapPort','162','162',1,1,'I','Port used for SNMP traps.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMTPFromAddr','netxms@localhost','netxms@localhost',1,0,'S','The address used for sending mail from.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMTPFromName','NetXMS Server','NetXMS Server',1,0,'S','The name used as the sender.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMTPPort','25','25',1,0,'I','Port used by SMTP server');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMTPRetryCount','1','1',1,0,'I','Number of retries for sending mail.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SMTPServer','localhost','localhost',1,0,'S','An SMTP server used for sending mail.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusCalculationAlgorithm','1','1',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusPollingInterval','60','60',1,1,'I','Interval in seconds between status polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusPropagationAlgorithm','1','1',1,1,'C','Algorithm for status propagation (how object''s status affects its child object statuses).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusShift','0','0',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusSingleThreshold','75','75',1,1,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusThresholds','503C2814','503C2814',1,1,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StatusTranslation','01020304','01020304',1,1,'S','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('StrictAlarmStatusFlow','0','0',1,0,'B','Enable/disable strict alarm status flow (alarm can be terminated only after it has been resolved).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SyncInterval','60','60',1,1,'I','Interval in seconds between writing object changes to the database.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SyncNodeNamesWithDNS','0','0',1,0,'B','Enable/disable synchronization of node names with DNS on each configuration poll.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SyslogIgnoreMessageTimestamp','0','0',1,0,'B','Ignore timestamp received in syslog messages and always use server time.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SyslogListenPort','514','514',1,1,'I','UDP port used by built-in syslog server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SyslogNodeMatchingPolicy','0','0',1,1,'C','Node matching policy for built-in syslog daemon.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('SyslogRetentionTime','90','90',1,0,'I','Retention time in days for records in syslog. All records older than specified will be deleted by housekeeping process.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('ThresholdRepeatInterval','0','0',1,1,'I','System-wide interval in seconds for resending threshold violation events. Value of 0 disables event resending.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('TileServerURL','http://tile.openstreetmap.org/','http://tile.openstreetmap.org/',1,0,'S','The URL for the Tile server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('TopologyDiscoveryRadius','3','3',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('TopologyExpirationTime','900','900',1,0,'I','');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('TopologyPollingInterval','1800','1800',1,1,'I','Interval in seconds between topology polls.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('TrapSourcesInAllZones','0','0',1,1,'B','Search all zones to match trap/syslog source address to node.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('UseDNSNameForDiscoveredNodes','0','0',1,0,'B','Enable/disable the use of DNS name instead of IP address as primary name for newly discovered nodes.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('UseFQDNForNodeNames','1','1',1,1,'B','Enable/disable the use of fully qualified domain names as primary names for newly discovered nodes.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('UseIfXTable','1','1',1,0,'B','Enable/disable the use of SNMP ifXTable instead of ifTable for interface configuration polling.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('UseInterfaceAliases','0','0',1,0,'C','Control usage of interface aliases (or descriptions).');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('UseSNMPTrapsForDiscovery','0','0',1,1,'B','Use SNMP trap information for new node discovery.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('UseSyslogForDiscovery','0','0',1,1,'B','Use syslog messages for new node discovery.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('XMPPLogin','netxms@localhost','netxms@localhost',1,1,'S','Login name that will be used to authentication on XMPP server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('XMPPPassword','netxms','netxms',1,1,'S','Password that will be used to authentication on XMPP server.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('XMPPPort','5222','5222',1,1,'I','XMPP connection port.');
INSERT INTO config (var_name,var_value,default_value,is_visible,need_server_restart,data_type,description) VALUES ('XMPPServer','localhost','localhost',1,1,'S','XMPP connection server.');
INSERT INTO config_values (var_name,var_value) VALUES ('ClientListenerPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('ExternalAuditPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('MobileDeviceListenerPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('RADIUSPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('RADIUSSecondaryPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('ReportingServerPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('SNMPPorts','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('SNMPTrapPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('SyslogListenPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('XMPPPort','65535');
INSERT INTO config_values (var_name,var_value) VALUES ('AllowedCiphers','63');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultAgentCacheMode','1','On');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultAgentCacheMode','2','Off');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultAgentProtocolCompressionMode','1','Enabled');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultAgentProtocolCompressionMode','2','Disabled');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultInterfaceExpectedState','0','UP');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultInterfaceExpectedState','1','DOWN');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultInterfaceExpectedState','2','IGNORE');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultEncryptionPolicy','0','Disabled');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultEncryptionPolicy','1','Allowed');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultEncryptionPolicy','2','Preferred');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('DefaultEncryptionPolicy','3','Required');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('StatusPropagationAlgorithm','0','Default');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('StatusPropagationAlgorithm','1','Unchanged');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('StatusPropagationAlgorithm','2','Fixed');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('StatusPropagationAlgorithm','3','Relative');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('StatusPropagationAlgorithm','4','Translated');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('UseInterfaceAliases','0','Don''t use aliases');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('UseInterfaceAliases','1','Use aliases when possible');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('UseInterfaceAliases','2','Concatenate alias and name');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('UseInterfaceAliases','3','Concatenate name and alias');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('SyslogNodeMatchingPolicy','0','IP,, then hostname');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('SyslogNodeMatchingPolicy','1','Hostname, then IP');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('LdapUserDeleteAction','0','Delete user');
INSERT INTO config_values (var_name,var_value,var_description) VALUES ('LdapUserDeleteAction','1','Disable user');
INSERT INTO users (id,name,password,system_access,flags,full_name,
                   description,grace_logins,auth_method,guid,
                   cert_mapping_method,cert_mapping_data,
                   auth_failures,last_passwd_change,min_passwd_length,
                   disabled_until,last_login)
   VALUES (0,'system',
           '3A445C0072CD69D9030CC6644020E5C4576051B1',
           274877906943,12,'','Built-in system account',5,0,
           '00000000-0000-0000-0000-000000000000',0,'',0,0,0,0,0
          );
INSERT INTO users (id,name,password,system_access,flags,full_name,
                   description,grace_logins,auth_method,guid,
                   cert_mapping_method,cert_mapping_data,
                   auth_failures,last_passwd_change,min_passwd_length,
                   disabled_until,last_login)
   VALUES (1,'admin',
           '3A445C0072CD69D9030CC6644020E5C4576051B1',
           0,8,'','Default administrator account',5,0,
           '00000000-0000-0000-0000-000000000000',0,'',0,0,0,0,0
          );
INSERT INTO user_groups (id,name,system_access,flags,description,guid)
   VALUES (-2147483648, 'Everyone', 274877906960, 0, 'Built-in everyone group',
           '00000000-0000-0000-0000-000000000000'
          );
INSERT INTO user_groups (id,name,system_access,flags,description,guid)
   VALUES (-2147483647, 'Admins', 549755813887, 0, 'Default administrative group',
           '00000000-0000-0000-0000-000000000000'
          );
INSERT INTO user_group_members (group_id,user_id) VALUES (-2147483647,1);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (1,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (2,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (3,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (4,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (5,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (6,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (7,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (8,-2147483647,524287);
INSERT INTO acl (object_id,user_id,access_rights) VALUES (9,-2147483647,524287);
INSERT INTO event_groups (id,name,description,range_start,range_end)
   VALUES (-2147483647,'NodeStatus','All events reporting about node status change',0,0);
INSERT INTO event_groups (id,name,description,range_start,range_end)
   VALUES (-2147483646,'NewObjects','All events reporting about new objects creation',0,0);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,6);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,8);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,7);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,9);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,10);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,11);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483647,12);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483646,1);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483646,2);
INSERT INTO event_group_members (group_id,event_code) VALUES (-2147483646,3);
INSERT INTO snmp_communities (id,community) VALUES(1,'public');
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  1, 'SYS_NODE_ADDED', '8d34acfd-dad6-4f6e-b6a8-1189683591ef',
  0, 1,
  'Node added',
  'Generated when new node object added to the database.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) "Discovered" flag - set to 1 if this node was added by network discovery process'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  2, 'SYS_SUBNET_ADDED', '75fc3f8b-768f-46b4-bf44-72949436a679',
  0, 0,
  'Subnet %2 added',
  'Generated when new subnet object added to the database.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Subnet object ID' || chr(13)||chr(10) ||
  '   2) Subnet name' || chr(13)||chr(10) ||
  '   3) IP address' || chr(13)||chr(10) ||
  '   4) Network mask'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  3, 'SYS_IF_ADDED', '33cb8f9c-9427-459c-8a71-45c73f5cc183',
  0, 1,
  'Interface "%2" added (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when new interface object added to the database.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4, 'SYS_IF_UP', '09ee209a-0e75-434f-b8c8-399d93305d7b',
  0, 1,
  'Interface "%2" changed state to UP (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface goes up.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  5, 'SYS_IF_DOWN', 'd9a6d46d-97f8-48eb-a86a-2c0f6b150d0d',
  2, 1,
  'Interface "%2" changed state to DOWN (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface goes down.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  45, 'SYS_IF_UNKNOWN', 'ecb47be1-f911-4c1f-9b00-d0d21694071d',
  1, 1,
  'Interface "%2" changed state to UNKNOWN (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface goes to unknown state.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  46, 'SYS_IF_DISABLED', '2f3123a2-425f-47db-9c6b-9bc05a7fba2d',
  0, 1,
  'Interface "%2" disabled (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface administratively disabled.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  47, 'SYS_IF_TESTING', 'eb500e5c-3560-4394-8f5f-80aa67036f13',
  0, 1,
  'Interface "%2" is testing (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface goes to testing state.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  62, 'SYS_IF_UNEXPECTED_UP', 'ff21a165-9253-4ecc-929a-ffd1e388d504',
  3, 1,
  'Interface "%2" unexpectedly changed state to UP (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface goes up but it''s expected state set to DOWN.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  63, 'SYS_IF_EXPECTED_DOWN', '911358f4-d2a1-4465-94d7-ce4bc5c38860',
  0, 1,
  'Interface "%2" with expected state DOWN changed state to DOWN (IP Addr: %3/%4, IfIndex: %5)',
  'Generated when interface goes down and it''s expected state is DOWN.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask' || chr(13)||chr(10) ||
  '   5) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  6, 'SYS_NODE_NORMAL', '03bc11c0-ec20-43be-be45-e60846f744dc',
  0, 1,
  'Node status changed to NORMAL',
  'Generated when node status changed to normal.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  7, 'SYS_NODE_WARNING', '1c80deab-aafb-43a7-93a7-1330dd563b47',
  1, 1,
  'Node status changed to WARNING',
  'Generated when node status changed to "Warning".' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  8, 'SYS_NODE_MINOR', '84eaea00-4ed7-41eb-9079-b783e5c60651',
  2, 1,
  'Node status changed to MINOR',
  'Generated when node status changed to "Minor Problem" (informational).' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  9, 'SYS_NODE_MAJOR', '27035c88-c27a-4c16-97b3-4658d34a5f63',
  3, 1,
  'Node status changed to MAJOR',
  'Generated when node status changed to "Major Problem".' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  10, 'SYS_NODE_CRITICAL', '8f2e98f8-1cd4-4e12-b41f-48b5c60ebe8e',
  4, 1,
  'Node status changed to CRITICAL',
  'Generated when node status changed to critical.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  11, 'SYS_NODE_UNKNOWN', '6933cce0-fe1f-4123-817f-af1fb9f0eab4',
  0, 1,
  'Node status changed to UNKNOWN',
  'Generated when node status changed to unknown.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  12, 'SYS_NODE_UNMANAGED', 'a8356ba7-51b7-4487-b74e-d12132db233c',
  0, 1,
  'Node status changed to UNMANAGED',
  'Generated when node status changed to unmanaged.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous node status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  13, 'SYS_NODE_CAP_CHANGED', 'b04e39f5-d3a7-4d9a-b594-37132f5eaf34',
  0, 0,
  'Node capabilities changed (Old: %1; New: %2)',
  'Generated when node capabilities changed.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Old capabilities' || chr(13)||chr(10) ||
  '   2) New capabilities'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  14, 'SYS_SNMP_UNREACHABLE', 'd2fc3b0c-1215-4a92-b8f3-47df5d753602',
  3, 1,
  'SNMP agent is not responding',
  'Generated when node''s SNMP agent is not responding.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  15, 'SYS_AGENT_UNREACHABLE', 'ba484457-3594-418e-a72a-65336055d025',
  3, 1,
  'Native agent is not responding',
  'Generated when node''s native agent is not responding.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  16, 'SYS_IF_DELETED', 'ad7e9856-e361-4095-9361-ccc462d93624',
  0, 1,
  'Interface "%2" deleted (IP Addr: %3/%4, IfIndex: %1)',
  'Generated when interface object deleted from the database.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name' || chr(13)||chr(10) ||
  '   3) Interface IP address' || chr(13)||chr(10) ||
  '   4) Interface netmask'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  17, 'SYS_THRESHOLD_REACHED', '05161c3d-7ceb-406f-af0a-af5c77f324a5',
  1, 1,
  'Threshold reached for data collection item "%2" (Parameter: %1; Threshold value: %3; Actual value: %4)',
  'Generated when threshold value reached for specific data collection item.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  18, 'SYS_THRESHOLD_REARMED', '25eef3a7-6158-4c5e-b4e3-8a7aa7ade73c',
  0, 1,
  'Threshold rearmed for data collection item %2 (Parameter: %1)',
  'Generated when threshold check is rearmed for specific data collection item.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) ||
  '   2) Item description' || chr(13)||chr(10) ||
  '   3) Data collection item ID' || chr(13)||chr(10) ||
  '   4) Instance' || chr(13)||chr(10) ||
  '   5) Threshold value' || chr(13)||chr(10) ||
  '   6) Actual value' || chr(13)||chr(10) ||
  '   7) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  19, 'SYS_SUBNET_DELETED', 'af188eb3-e84f-4fd9-aecf-f1ba934a9f1a',
  0, 0,
  'Subnet %2 deleted',
  'Generated when subnet object deleted from the database.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Subnet object ID' || chr(13)||chr(10) ||
  '   2) Subnet name' || chr(13)||chr(10) ||
  '   3) IP address' || chr(13)||chr(10) ||
  '   4) Network mask'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  20, 'SYS_THREAD_HANG', 'df247d13-a63a-43fe-bb02-cb41718ee387',
  4, 1,
  'Thread "%1" is not responding',
  'Generated when one of the system threads hangs or stops unexpectedly.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Thread name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  21, 'SYS_THREAD_RUNNING', '5589f6ce-7133-44db-8e7a-e1452d636a9a',
  0, 1,
  'Thread "%1" was returned to running state',
  'Generated when one of the system threads which previously hangs or stops unexpectedly was returned to running state.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Thread name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  22, 'SYS_SMTP_FAILURE', '1e376009-0d26-4b86-87a2-f4715a02fb38',
  1, 1,
  'Unable to send e-mail to <%3>: %2',
  'Generated when server is unable to send e-mail.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Error code' || chr(13)||chr(10) ||
  '   2) Error text' || chr(13)||chr(10) ||
  '   3) Recipient address' || chr(13)||chr(10) ||
  '   4) Mail subject'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  23, 'SYS_MAC_ADDR_CHANGED', '61916ef0-1eee-4df7-a95b-150928d47962',
  1, 1,
  'MAC address for interface %3 changed from %4 to %5',
  'Generated when server detects change of interface''s MAC address.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface index' || chr(13)||chr(10) ||
  '   3) Interface name' || chr(13)||chr(10) ||
  '   4) Old MAC address' || chr(13)||chr(10) ||
  '   5) New MAC address'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  24, 'SYS_INCORRECT_NETMASK', '86c08c55-416e-4ac4-bf2b-302b5fddbd68',
  2, 1,
  'Invalid network mask /%4 on interface "%3", should be /%5',
  'Generated when server detects invalid network mask on an interface.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface object ID' || chr(13)||chr(10) ||
  '   2) Interface index' || chr(13)||chr(10) ||
  '   3) Interface name' || chr(13)||chr(10) ||
  '   4) Actual network mask on interface' || chr(13)||chr(10) ||
  '   5) Correct network mask'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  28, 'SYS_NODE_DOWN', 'ce34f0d0-5b21-48c2-8788-8ed5ee547023',
  4, 1,
  'Node down',
  'Generated when node is not responding to management server.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Previous state (1 if node was marked as UNREACHABLE, 0 if it was marked as DOWN)'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  29, 'SYS_NODE_UP', '05f180b6-62e7-4bc4-8a8d-34540214254b',
  0, 1,
  'Node up',
  'Generated when communication with the node re-established.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No event-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  25, 'SYS_SERVICE_DOWN', '89caacb5-d2cf-493b-862f-cddbfecac5b6',
  3, 1,
  'Network service "%1" is not responding',
  'Generated when network service is not responding to management server as expected.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Service name' || chr(13)||chr(10) ||
  '   2) Service object ID' || chr(13)||chr(10) ||
  '   3) Service type'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  26, 'SYS_SERVICE_UP', 'ab35e7c7-2428-44db-ad43-57fe551bb8cc',
  0, 1,
  'Network service "%1" returned to operational state',
  'Generated when network service responds as expected after failure.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Service name' || chr(13)||chr(10) ||
  '   2) Service object ID' || chr(13)||chr(10) ||
  '   3) Service type'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  27, 'SYS_SERVICE_UNKNOWN', 'd891adae-49fe-4442-a8f3-0ca37ca8820a',
  1, 1,
  'Status of network service "%1" is unknown',
  'Generated when management server is unable to determine state of the network service due to agent or server-to-agent communication failure.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Service name' || chr(13)||chr(10) ||
  '   2) Service object ID' || chr(13)||chr(10) ||
  '   3) Service type'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  30, 'SYS_SMS_FAILURE', 'c349bf75-458a-4d43-9c27-f71ea4bb06e2',
  1, 1,
  'Unable to send SMS to phone %1',
  'Generated when server is unable to send SMS.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Phone number'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  31, 'SYS_SNMP_OK', 'a821086b-1595-40db-9148-8d770d30a54b',
  0, 1,
  'Connectivity with SNMP agent restored',
  'Generated when connectivity with node''s SNMP agent restored.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  32, 'SYS_AGENT_OK', '9c15299a-f2e3-4440-84c5-b17dea87ae2a',
  0, 1,
  'Connectivity with native agent restored',
  'Generated when connectivity with node''s native agent restored.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  33, 'SYS_SCRIPT_ERROR', '2cc78efe-357a-4278-932f-91e36754c775',
  2, 1,
  'Script (%1) execution error: %2',
  'Generated when server encounters NXSL script execution error.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Script name' || chr(13)||chr(10) ||
  '   2) Error text' || chr(13)||chr(10) ||
  '   3) DCI ID if script is DCI transformation script, or 0 otherwise'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  34, 'SYS_CONDITION_ACTIVATED', '16a86780-b73a-4601-929c-0c503bd06401',
  2, 1,
  'Condition "%2" activated',
  'Default event for condition activation.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Condition object ID' || chr(13)||chr(10) ||
  '   2) Condition object name' || chr(13)||chr(10) ||
  '   3) Previous condition status' || chr(13)||chr(10) ||
  '   4) Current condition status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  35, 'SYS_CONDITION_DEACTIVATED', '926d15d2-9761-4bb6-a1ce-64175303796f',
  0, 1,
  'Condition "%2" deactivated',
  'Default event for condition deactivation.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Condition object ID' || chr(13)||chr(10) ||
  '   2) Condition object name' || chr(13)||chr(10) ||
  '   3) Previous condition status' || chr(13)||chr(10) ||
  '   4) Current condition status'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  36, 'SYS_DB_CONN_LOST', '0311e9c8-8dcf-4a5b-9036-8cff034409ff',
  4, 1,
  'Lost connection with backend database engine',
  'Generated if connection with backend database engine is lost.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  37, 'SYS_DB_CONN_RESTORED', 'd36259a7-5f6b-4e3c-bb6f-17d1f8ac950d',
  0, 1,
  'Connection with backend database engine restored',
  'Generated when connection with backend database engine restored.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  38, 'SYS_CLUSTER_RESOURCE_MOVED', '44abe5f3-a7c9-4fbd-8d10-53be172eae83',
  1, 1,
  'Cluster resource "%2" moved from node %4 to node %6',
  'Generated when cluster resource moved between nodes.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Resource ID' || chr(13)||chr(10) ||
  '   2) Resource name' || chr(13)||chr(10) ||
  '   3) Previous owner node ID' || chr(13)||chr(10) ||
  '   4) Previous owner node name' || chr(13)||chr(10) ||
  '   5) New owner node ID' || chr(13)||chr(10) ||
  '   6) New owner node name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  39, 'SYS_CLUSTER_RESOURCE_DOWN', 'c3b1d4b5-2e41-4a2f-b379-9d74ebba3a25',
  3, 1,
  'Cluster resource "%2" is down (last owner was %4)',
  'Generated when cluster resource goes down.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Resource ID' || chr(13)||chr(10) ||
  '   2) Resource name' || chr(13)||chr(10) ||
  '   3) Last owner node ID' || chr(13)||chr(10) ||
  '   4) Last owner node name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  40, 'SYS_CLUSTER_RESOURCE_UP', 'ef6fff96-0cbb-4030-aeba-2473a80c6568',
  0, 1,
  'Cluster resource "%2" is up (new owner is %4)',
  'Generated when cluster resource goes up.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Resource ID' || chr(13)||chr(10) ||
  '   2) Resource name' || chr(13)||chr(10) ||
  '   3) New owner node ID' || chr(13)||chr(10) ||
  '   4) New owner node name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  41, 'SYS_CLUSTER_DOWN', '8f14d0f7-08d4-4422-92f4-469e5eef93ef',
  4, 1,
  'Cluster is down',
  'Generated when cluster goes down.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  42, 'SYS_CLUSTER_UP', '4a9cdb65-aa44-42f2-99b0-1e302aec10f6',
  0, 1,
  'Cluster is up',
  'Generated when cluster goes up.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No message-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  43, 'SYS_ALARM_TIMEOUT', '4ae4f601-327b-4ef8-9740-8600a1ba2acd',
  1, 1,
  'Alarm timeout expired (ID: %1; Text: %2)',
  'Generated when alarm timeout expires.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Alarm ID' || chr(13)||chr(10) ||
  '   2) Alarm message' || chr(13)||chr(10) ||
  '   3) Alarm key' || chr(13)||chr(10) ||
  '   4) Original event code'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  44, 'SYS_LOG_RECORD_MATCHED', 'd9326159-5c60-410f-990e-fae88df7fdd4',
  1, 1,
  'Log record matched (Policy: %1; File: %2; Record: %4)',
  'Default event for log record match.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Policy name' || chr(13)||chr(10) ||
  '   2) Log file name' || chr(13)||chr(10) ||
  '   3) Matching regular expression' || chr(13)||chr(10) ||
  '   4) Matched record' || chr(13)||chr(10) ||
  '   5 .. 9) Reserved' || chr(13)||chr(10) ||
  '   10 .. 99) Substrings extracted by regular expression'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  48, 'SYS_EVENT_STORM_DETECTED', 'c98d8575-d134-4044-ba67-75c5f5d0f6e0',
  3, 1,
  'Event storm detected (Events per second: %1)',
  'Generated when system detects an event storm.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Events per second' || chr(13)||chr(10) ||
  '   2) Duration' || chr(13)||chr(10) ||
  '   3) Threshold'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  49, 'SYS_EVENT_STORM_ENDED', 'dfd5e3ba-3182-4deb-bc32-9e6b8c1c6546',
  0, 1,
  'Event storm ended',
  'Generated when system clears event storm condition.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Events per second' || chr(13)||chr(10) ||
  '   2) Duration' || chr(13)||chr(10) ||
  '   3) Threshold'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  50, 'SYS_NETWORK_CONN_LOST', '3cb0921a-87a1-46e4-8be1-82ad2dda0015',
  4, 1,
  'NetXMS server network connectivity lost',
  'Generated when system detects loss of network connectivity based on beacon probing.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Number of beacons'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  51, 'SYS_NETWORK_CONN_RESTORED', '1c61b3e0-389a-47ac-8469-932a907392bc',
  0, 1,
  'NetXMS server network connectivity restored',
  'Generated when system detects restoration of network connectivity based on beacon probing.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Number of beacons'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  52, 'SYS_DB_QUERY_FAILED', '5f35d646-63b6-4dcd-b94a-e2ccd060686a',
  4, 1,
  'Database query failed (Query: %1; Error: %2)',
  'Generated when SQL query to backend database failed.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Query' || chr(13)||chr(10) ||
  '   2) Error message'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  53, 'SYS_DCI_UNSUPPORTED', '28367b5b-1541-4526-8cbe-91a17ed31ba4',
  2, 1,
  'Status of DCI %1 (%5: %2) changed to UNSUPPORTED',
  'Generated when DCI status changed to UNSUPPORTED.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) DCI ID' || chr(13)||chr(10) ||
  '   2) DCI Name' || chr(13)||chr(10) ||
  '   3) DCI Description' || chr(13)||chr(10) ||
  '   4) DCI Origin code' || chr(13)||chr(10) ||
  '   5) DCI Origin name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  54, 'SYS_DCI_DISABLED', '50196042-0619-4420-9471-16b7c25c0213',
  1, 1,
  'Status of DCI %1 (%5: %2) changed to DISABLED',
  'Generated when DCI status changed to DISABLED.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) DCI ID' || chr(13)||chr(10) ||
  '   2) DCI Name' || chr(13)||chr(10) ||
  '   3) DCI Description' || chr(13)||chr(10) ||
  '   4) DCI Origin code' || chr(13)||chr(10) ||
  '   5) DCI Origin name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  55, 'SYS_DCI_ACTIVE', '740b6810-b355-46f4-a921-65118504af18',
  0, 1,
  'Status of DCI %1 (%5: %2) changed to ACTIVE',
  'Generated when DCI status changed to ACTIVE.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) DCI ID' || chr(13)||chr(10) ||
  '   2) DCI Name' || chr(13)||chr(10) ||
  '   3) DCI Description' || chr(13)||chr(10) ||
  '   4) DCI Origin code' || chr(13)||chr(10) ||
  '   5) DCI Origin name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  56, 'SYS_IP_ADDRESS_CHANGED', '517b6d2a-f5c6-46aa-969d-48e62e05e3bf',
  1, 1,
  'Primary IP address changed from %2 to %1',
  'Generated when primary IP address changed (usually because of primary name change or DNS change).' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) New IP address' || chr(13)||chr(10) ||
  '   2) Old IP address' || chr(13)||chr(10) ||
  '   3) Primary host name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  57, 'SYS_8021X_PAE_STATE_CHANGED', '3c69667b-04dd-434a-a5b7-cf322abcb9c9',
  0, 1,
  'Port %6 PAE state changed from %4 to %2',
  'Generated when switch port PAE state changed.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) New PAE state code' || chr(13)||chr(10) ||
  '   2) New PAE state as text' || chr(13)||chr(10) ||
  '   3) Old PAE state code' || chr(13)||chr(10) ||
  '   4) Old PAE state as text' || chr(13)||chr(10) ||
  '   5) Interface index' || chr(13)||chr(10) ||
  '   6) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  58, 'SYS_8021X_BACKEND_STATE_CHANGED', 'e7cddb93-e42d-479d-9c5b-d68d4be71319',
  0, 1,
  'Port %6 backend authentication state changed from %4 to %2',
  'Generated when switch port backend authentication state changed.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) New backend state code' || chr(13)||chr(10) ||
  '   2) New backend state as text' || chr(13)||chr(10) ||
  '   3) Old backend state code' || chr(13)||chr(10) ||
  '   4) Old backend state as text' || chr(13)||chr(10) ||
  '   5) Interface index' || chr(13)||chr(10) ||
  '   6) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  59, 'SYS_8021X_PAE_FORCE_UNAUTH', '16a59549-2b06-4938-9e79-1c3d9445d65c',
  3, 1,
  'Port %2 switched to force unauthorize state',
  'Generated when switch port PAE state changed to FORCE UNAUTHORIZE.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  60, 'SYS_8021X_AUTH_FAILED', '8ac56c8e-150b-40d1-998a-c63e7d6596b8',
  3, 1,
  '802.1x authentication failed on port %2',
  'Generated when switch port backend authentication state changed to FAIL.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  61, 'SYS_8021X_AUTH_TIMEOUT', '609a2198-2ea4-4d07-8100-587d550a5670',
  3, 1,
  '802.1x authentication time out on port %2',
  'Generated when switch port backend authentication state changed to TIMEOUT.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  64, 'SYS_CONTAINER_AUTOBIND', '611133e2-1f76-446f-b278-9d500a823611',
  0, 1,
  'Node %2 automatically bound to container %4',
  'Generated when node bound to container object by autobind rule.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Node ID' || chr(13)||chr(10) ||
  '   2) Node name' || chr(13)||chr(10) ||
  '   3) Container ID' || chr(13)||chr(10) ||
  '   4) Container name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  65, 'SYS_CONTAINER_AUTOUNBIND', 'e57603be-0d81-41aa-b07c-12d08640561c',
  0, 1,
  'Node %2 automatically unbound from container %4',
  'Generated when node unbound from container object by autobind rule.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Node ID' || chr(13)||chr(10) ||
  '   2) Node name' || chr(13)||chr(10) ||
  '   3) Container ID' || chr(13)||chr(10) ||
  '   4) Container name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  66, 'SYS_TEMPLATE_AUTOAPPLY', 'bf084945-f928-4428-8c6c-d2965addc832',
  0, 1,
  'Template %4 automatically applied to node %2',
  'Generated when template applied to node by autoapply rule.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Node ID' || chr(13)||chr(10) ||
  '   2) Node name' || chr(13)||chr(10) ||
  '   3) Template ID' || chr(13)||chr(10) ||
  '   4) Template name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  67, 'SYS_TEMPLATE_AUTOREMOVE', '8f7a4b4a-d0a2-4404-9b66-fdbc029f42cf',
  0, 1,
  'Template %4 automatically removed from node %2',
  'Generated when template removed from node by autoapply rule.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Node ID' || chr(13)||chr(10) ||
  '   2) Node name' || chr(13)||chr(10) ||
  '   3) Template ID' || chr(13)||chr(10) ||
  '   4) Template name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  68, 'SYS_NODE_UNREACHABLE', '47bba2ce-c795-4e56-ad44-cbf05741e1ff',
  4, 1,
  'Node unreachable because of network failure',
  'Generated when node is unreachable by management server because of network failure.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   No event-specific parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  69, 'SYS_TABLE_THRESHOLD_ACTIVATED', 'c08a1cfe-3128-40c2-99cc-378e7ef91f79',
  2, 1,
  'Threshold activated on table "%2" row %4 (%5)',
  'Generated when table threshold is activated.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Table DCI name' || chr(13)||chr(10) ||
  '   2) Table DCI description' || chr(13)||chr(10) ||
  '   3) Table DCI ID' || chr(13)||chr(10) ||
  '   4) Table row' || chr(13)||chr(10) ||
  '   5) Instance'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  70, 'SYS_TABLE_THRESHOLD_DEACTIVATED', '479085e7-e1d1-4f2a-9d96-1d522f51b26a',
  0, 1,
  'Threshold deactivated on table "%2" row %4 (%5)',
  'Generated when table threshold is deactivated.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Table DCI name' || chr(13)||chr(10) ||
  '   2) Table DCI description' || chr(13)||chr(10) ||
  '   3) Table DCI ID' || chr(13)||chr(10) ||
  '   4) Table row' || chr(13)||chr(10) ||
  '   5) Instance'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  71, 'SYS_IF_PEER_CHANGED', 'a3a5c1df-9d96-4e98-9e06-b3157dbf39f0',
  0, 1,
  'New peer for interface %3 is %7 interface %10 (%12)',
  'Generated when peer information for interface changes.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '    1) Local interface object ID' || chr(13)||chr(10) ||
  '    2) Local interface index' || chr(13)||chr(10) ||
  '    3) Local interface name' || chr(13)||chr(10) ||
  '    4) Local interface IP address' || chr(13)||chr(10) ||
  '    5) Local interface MAC address' || chr(13)||chr(10) ||
  '    6) Peer node object ID' || chr(13)||chr(10) ||
  '    7) Peer node name' || chr(13)||chr(10) ||
  '    8) Peer interface object ID' || chr(13)||chr(10) ||
  '    9) Peer interface index' || chr(13)||chr(10) ||
  '   10) Peer interface name' || chr(13)||chr(10) ||
  '   11) Peer interface IP address' || chr(13)||chr(10) ||
  '   12) Peer interface MAC address' || chr(13)||chr(10) ||
      '   13) Discovery protocol'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  72, 'SYS_AP_ADOPTED', '5aaee261-0c5d-44e0-b2f0-223bbba5297d',
  0, 1,
  'Access point %2 changed state to ADOPTED',
  'Generated when access point state changes to ADOPTED.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '    1) Access point object ID' || chr(13)||chr(10) ||
  '    2) Access point name' || chr(13)||chr(10) ||
  '    3) Access point MAC address' || chr(13)||chr(10) ||
  '    4) Access point IP address' || chr(13)||chr(10) ||
  '    5) Access point vendor name' || chr(13)||chr(10) ||
  '    6) Access point model' || chr(13)||chr(10) ||
  '    7) Access point serial number'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  73, 'SYS_AP_UNADOPTED', '846a3581-aad1-4e17-9c55-9bd2e6b1247b',
  3, 1,
  'Access point %2 changed state to UNADOPTED',
  'Generated when access point state changes to UNADOPTED.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '    1) Access point object ID' || chr(13)||chr(10) ||
  '    2) Access point name' || chr(13)||chr(10) ||
  '    3) Access point MAC address' || chr(13)||chr(10) ||
  '    4) Access point IP address' || chr(13)||chr(10) ||
  '    5) Access point vendor name' || chr(13)||chr(10) ||
  '    6) Access point model' || chr(13)||chr(10) ||
  '    7) Access point serial number'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  74, 'SYS_AP_DOWN', '2c8c6208-d3ab-4b8c-926a-872f4d8abcee',
  4, 1,
  'Access point %2 changed state to DOWN',
  'Generated when access point state changes to DOWN.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '    1) Access point object ID' || chr(13)||chr(10) ||
  '    2) Access point name' || chr(13)||chr(10) ||
  '    3) Access point MAC address' || chr(13)||chr(10) ||
  '    4) Access point IP address' || chr(13)||chr(10) ||
  '    5) Access point vendor name' || chr(13)||chr(10) ||
  '    6) Access point model' || chr(13)||chr(10) ||
  '    7) Access point serial number'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      75, 'SYS_IF_MASK_CHANGED', 'f800e593-057e-4aec-9e47-be0f7718c5c4',
      0, 1,
      'Interface "%2" changed mask from /%6 to /%4 (IP Addr: %3/%4, IfIndex: %5)',
      'Generated when when network mask on interface is corrected.' || chr(13)||chr(10) ||
      'Parameters:' || chr(13)||chr(10) ||
      '   1) Interface object ID' || chr(13)||chr(10) ||
      '   2) Interface name' || chr(13)||chr(10) ||
      '   3) Interface IP address' || chr(13)||chr(10) ||
      '   4) Interface netmask' || chr(13)||chr(10) ||
      '   5) Interface index' || chr(13)||chr(10) ||
      '   6) Interface old mask'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      76, 'SYS_IF_IPADDR_ADDED', '475bdca6-543e-410b-9aff-c217599e0fe6',
      0, 1,
      'IP address %3/%4 added to interface "%2"',
      'Generated when IP address added to interface.' || chr(13)||chr(10) ||
      'Parameters:' || chr(13)||chr(10) ||
      '   1) Interface object ID' || chr(13)||chr(10) ||
      '   2) Interface name' || chr(13)||chr(10) ||
      '   3) IP address' || chr(13)||chr(10) ||
      '   4) Network mask' || chr(13)||chr(10) ||
      '   5) Interface index'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      77, 'SYS_IF_IPADDR_DELETED', 'ef477387-eb50-4a1a-bf90-717502b9873c',
      0, 1,
      'IP address %3/%4 deleted from interface "%2"',
      'Generated when IP address deleted from interface.' || chr(13)||chr(10) ||
      'Parameters:' || chr(13)||chr(10) ||
      '   1) Interface object ID' || chr(13)||chr(10) ||
      '   2) Interface name' || chr(13)||chr(10) ||
      '   3) IP address' || chr(13)||chr(10) ||
      '   4) Network mask' || chr(13)||chr(10) ||
      '   5) Interface index'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      78, 'SYS_MAINTENANCE_MODE_ENTERED', '5f6c8b1c-f162-413e-8028-80e7ad2c362d',
      0, 1,
      'Entered maintenance mode',
      'Generated when node, cluster, or mobile device entered maintenance mode.'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      79, 'SYS_MAINTENANCE_MODE_LEFT', 'cab06848-a622-430d-8b4c-addeea732657',
      0, 1,
      'Left maintenance mode',
      'Generated when node, cluster, or mobile device left maintenance mode.'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      80, 'SYS_LDAP_SYNC_ERROR', 'f7e8508d-1503-4736-854b-1e5b8b0ad1f2',
      3, 1,
      'LDAP sync error: %5',
      'Generated when LDAP synchronization error occurs.' || chr(13)||chr(10) ||
      'Parameters:' || chr(13)||chr(10) ||
      '   1) User ID' || chr(13)||chr(10) ||
      '   2) User GUID' || chr(13)||chr(10) ||
      '   3) User LDAP DN' || chr(13)||chr(10) ||
      '   4) User name' || chr(13)||chr(10) ||
      '   5) Problem description'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      81, 'SYS_AGENT_LOG_PROBLEM', '262057ca-357a-4a4d-9b78-42ae96e490a1',
      3, 1,
      'Problem with agent log: %2',
      'Generated on status poll if agent reports problem with log file.' || chr(13)||chr(10) ||
      'Parameters:' || chr(13)||chr(10) ||
      '   1) Status code' || chr(13)||chr(10) ||
      '   2) Description'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
   (
      82, 'SYS_AGENT_LOCAL_DATABASE_PROBLEM', 'd02b63f1-1151-429e-adb9-1dfbb3a31b32',
      3, 1,
      'Problem with agent local database: %2',
      'Generated on status poll if agent reports local database problem.' || chr(13)||chr(10) ||
      'Parameters:' || chr(13)||chr(10) ||
      '   1) Status code' || chr(13)||chr(10) ||
      '   2) Description'
   );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  83, 'SYS_IF_EXPECTED_STATE_UP', '4997c3f5-b332-4077-8e99-983142f0e193',
  0, 1,
  'Expected state for interface "%2" set to UP',
  'Generated when interface expected state set to UP.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  84, 'SYS_IF_EXPECTED_STATE_DOWN', '75de536c-4861-4f19-ba56-c43d814431d7',
  0, 1,
  'Expected state for interface "%2" set to DOWN',
  'Generated when interface expected state set to DOWN.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  85, 'SYS_IF_EXPECTED_STATE_IGNORE', '0e488c0e-3340-4e02-ad96-b999b8392e55',
  0, 1,
  'Expected state for interface "%2" set to IGNORE',
  'Generated when interface expected state set to IGNORE.' || chr(13)||chr(10) ||
  'Please note that source of event is node, not an interface itself.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Interface index' || chr(13)||chr(10) ||
  '   2) Interface name'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  86, 'SYS_ROUTING_LOOP_DETECTED', '98276f42-dc85-41a5-b449-6ba83d1a71b7',
  3, 1,
  'Routing loop detected for destination %3 (selected route %6/%7 via %9)',
  'Generated when server detects routing loop during network path trace.' || chr(13)||chr(10) ||
  'Source of the event is node which routes packet back to already passed hop.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Protocol (IPv4 or IPv6)' || chr(13)||chr(10) ||
  '   2) Path trace destination node ID' || chr(13)||chr(10) ||
  '   3) Path trace destination address' || chr(13)||chr(10) ||
  '   4) Path trace source node ID' || chr(13)||chr(10) ||
  '   5) Path trace source node address' || chr(13)||chr(10) ||
  '   6) Routing prefix (subnet address)' || chr(13)||chr(10) ||
  '   7) Routing prefix length (subnet mask length)' || chr(13)||chr(10) ||
  '   8) Next hop node ID' || chr(13)||chr(10) ||
  '   9) Next hop address'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  87, 'SYS_PACKAGE_INSTALLED', '92e5cf98-a415-4414-9ad8-d155dac77e96',
  0, 1,
  'Package %1 %2 installed',
  'Generated when new software package is found.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Package name' || chr(13)||chr(10) ||
  '   2) Package version'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  88, 'SYS_PACKAGE_UPDATED', '9d5878c1-525e-4cab-8f02-2a6c46d7fc36',
  0, 1,
  'Package %1 updated from %3 to %2',
  'Generated when software package version change is detected.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Package name' || chr(13)||chr(10) ||
  '   2) New package version' || chr(13)||chr(10) ||
  '   3) Old package version'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  89, 'SYS_PACKAGE_REMOVED', '6ada4ea4-43e4-4444-9d19-ef7366110bb9',
  0, 1,
  'Package %1 %2 removed',
  'Generated when software package removal is detected.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Package name' || chr(13)||chr(10) ||
  '   2) Last known package version'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  500, 'SNMP_UNMATCHED_TRAP', 'fc3613f7-d151-4221-9acd-d28b6f804335',
  0, 1,
  'SNMP trap received: %1 (Parameters: %2)',
  'Generated when system receives an SNMP trap without match in trap configuration table' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID' || chr(13)||chr(10) ||
  '   2) Trap parameters'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  501, 'SNMP_COLD_START', '39920e99-97bd-4d61-a462-43f89ba6fbdf',
  0, 1,
  'System was cold-started',
  'Generated when system receives a coldStart SNMP trap' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  502, 'SNMP_WARM_START', '0aa888c1-eba6-4fe7-a37a-b85f2b373bdc',
  0, 1,
  'System was warm-started',
  'Generated when system receives a warmStart SNMP trap' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  503, 'SNMP_LINK_DOWN', 'b71338cc-137d-473c-a0f1-6b131086af56',
  3, 1,
  'Link is down (interface index %2)',
  'Generated when system receives a linkDown SNMP trap' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID' || chr(13)||chr(10) ||
  '   2) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  504, 'SNMP_LINK_UP', '03da14a7-e39c-4a46-a7cb-4bf77ec7936c',
  0, 1,
  'Link is up (interface index %2)',
  'Generated when system receives a linkUp SNMP trap' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID' || chr(13)||chr(10) ||
  '   2) Interface index'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  505, 'SNMP_AUTH_FAILURE', '37020cb0-dde7-487b-9cfb-0d5ee771aa13',
  1, 1,
  'SNMP authentication failure',
  'Generated when system receives an authenticationFailure SNMP trap' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  506, 'SNMP_EGP_NEIGHBOR_LOSS', 'aecf5fa4-390c-4125-be10-df8b0e669fe1',
  1, 1,
  'EGP neighbor loss',
  'Generated when system receives an egpNeighborLoss SNMP trap' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) SNMP trap OID'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4000, 'DC_HIGH_CPU_UTIL', 'a1063661-6992-4536-bb11-38e40d72537f',
  2, 1,
  'CPU utilization exceeds allowed maximum of %3 (Current: %4)',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4001, 'DC_HIGH_PROCLOAD', '6b438fb0-0bfa-4832-b020-8432a2a417d3',
  2, 1,
  'Processor load average exceeds allowed maximum of %3 (Current: %4)',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4002, 'DC_HOST_RESTARTED', 'e0d27cf7-017d-4a73-ba53-bb6a4adcb9ee',
  0, 1,
  'Host was restarted within last 5 minutes',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4003, 'DC_AGENT_RESTARTED', '191fc8fb-d10f-46c4-b100-1167074622e8',
  0, 1,
  'NetXMS agent was restarted within last 5 minutes',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4004, 'DC_SERVICE_NOT_RUNNING', '69f9fb0b-c7cf-47dd-b1df-bfc2c5639678',
  3, 1,
  'Service "%6" is not running',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4005, 'DC_MAILBOX_TOO_LARGE', '4d448490-88af-450a-a138-b127b89a0f06',
  1, 1,
  'Mailbox "%6" exceeds size limit (allowed size: %3; actual size: %4)',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4006, 'DC_AGENT_VERSION_CHANGE', 'e3c5d5e0-37c7-4b84-842e-22ad64a18692',
  0, 1,
  'NetXMS agent version was changed from %3 to %4',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4007, 'DC_HOSTNAME_CHANGE', 'd9f6d75c-5924-4e91-94e1-e43e6adc72c7',
  1, 1,
  'Host name was changed from %3 to %4',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4008, 'DC_FILE_CHANGE', '9da39f09-b5d1-4b04-9b46-e4fb7e5751da',
  1, 1,
  'File "%6" was changed',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4009, 'DC_HDD_TEMP_WARNING', 'fe37a161-9411-4869-8b3c-81041694609a',
  1, 1,
  'Temperature of hard disk %6 is above warning level of %3 (current: %4)',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4010, 'DC_HDD_TEMP_MAJOR', '8af75da6-69f6-4723-902f-d43b44d5fced',
  3, 1,
  'Temperature of hard disk %6 is above %3 (current: %4)',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_cfg (event_code,event_name,guid,severity,flags,message,description) VALUES
 (
  4011, 'DC_HDD_TEMP_CRITICAL', 'ed2a5b3c-02a0-4d81-94c9-7dc6396d6256',
  4, 1,
  'Temperature of hard disk %6 is above critical level of %3 (current: %4)',
  'Custom data collection threshold event.' || chr(13)||chr(10) ||
  'Parameters:' || chr(13)||chr(10) ||
  '   1) Parameter name' || chr(13)||chr(10) || '   2) Item description' || chr(13)||chr(10) || '   3) Threshold value' || chr(13)||chr(10) || '   4) Actual value' || chr(13)||chr(10) || '   5) Data collection item ID' || chr(13)||chr(10) || '   6) Instance' || chr(13)||chr(10) || '   7) Repeat flag' || chr(13)||chr(10) || '   8) Current DCI value'
 );
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (0,'2d2c3d32-49d4-4e76-b6aa-782b30d90f28',7944,'Show alarm when node is down',
  '%m',5,'NODE_DOWN_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (1,'4fa20604-b56f-4f12-bb5d-2c3243a126c9',7944,'Terminate node down alarms when node is up',
  '%m',6,'NODE_DOWN_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (2,'45999e66-c16c-400d-8b79-63a0f8eb8958',7944,'Show alarm when network service is down or in unknown state',
  '%m',5,'SERVICE_DOWN_%i_%2','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (3,'8537fa14-e824-435b-a1dd-be3e566b7f67',7944,'Terminate network service down/unknown alarms when service is up',
  '%m',6,'SERVICE_DOWN_%i_%2','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (4,'2daa869f-9921-4f3e-9cb9-004c2f31f70a',7944,'Show alarm when interface is down',
  '%m',5,'IF_DOWN_%i_%5','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (5,'95d42ff2-6fe0-4b1c-9c7b-c18520393f9f',7944,'Terminate interface down alarms when interface is up',
  '%m',6,'IF_DOWN_%i_%5','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (6,'6f46d451-ee66-4563-8747-d129877df24d',7944,'Terminate interface down alarms when interface is deleted or it''s expected state changed',
  '%m',6,'IF_DOWN_%i_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (7,'727a0dca-ecc4-4490-bf4b-3fc8b5ff8cb4',7944,'Show alarm when interface is unexpectedly up',
  '%m',5,'IF_UNEXP_UP_%i_%5','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (8,'11176d3e-0149-448b-a5fe-2be764762775',7944,'Terminate interface unexpectedly up alarms when interface goes down',
  '%m',6,'IF_UNEXP_UP_%i_%5','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (9,'ecc3fb57-672d-489d-a0ef-4214ea896e0f',7944,'Terminate interface unexpectedly up alarms when interface is deleted or it''s expected state changed',
  '%m',6,'IF_UNEXP_UP_%i_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (10,'5b8115f1-5c84-443a-9f88-18fc0b70f29e',7944,'Generate alarm when incorrect network mask detected on interface',
  '%m',2,'BAD_NETMASK_%i_%2','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (11,'062228ef-c155-4669-a90a-603cae13240e',7944,'Generate alarm when server enconters NXSL script execution error',
  '%m',2,'SCRIPT_ERR_%1_%2','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (12,'943f5474-5614-44e1-820c-b8fe17bc4d0b',7944,'Show alarm when connection with backend database is lost',
  '%m',4,'DB_CONN','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (13,'9ce6b59e-e274-4c94-b314-4b4652c10c74',7944,'Terminate DB connection loss alarm when connection restored',
  '%m',6,'DB_CONN','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (14,'e3120a33-216e-e048-aa3b-4f1a9f3f10fc',7944,'Show alarm when NetXMS server network connection is lost',
  '%m',4,'NET_CONN_LOST','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (15,'bdc76d2e-967e-bf44-95a1-a229ef8b3ff4',7944,'Terminate NetXMS server network connection loss alarm when connection restored',
  '%m',6,'NET_CONN_LOST','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (16,'226de02d-9eb2-4ea1-a92c-2bdb1718f2ec',7944,'Show alarm when DCI status changes to DISABLED or UNSUPPORTED',
  '%m',5,'DCI_STATUS_%i_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (17,'02a21afe-c942-4953-8b5d-f463c597cff8',7944,'Terminate DCI status alarms when DCI status returns to ACTIVE',
  '%m',6,'DCI_STATUS_%i_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (18,'47fd5c32-b6c9-48b8-99fb-c389dde63bee',7944,'Generate alarm on threshold violation',
  '%m',5,'DC_THRESHOLD_%i_%<dciId>','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (19,'dcdd6f93-2f9e-4c3e-97cb-95b6847f13ce',7944,'Terminate threshold violation alarms',
  '%m',6,'DC_THRESHOLD_%i_%<dciId>','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (20,'d3acabe8-717d-4ceb-bb7f-498d5af898f2',7944,'Generate alarm on table threshold violation',
  '%m',5,'DCTTHR_%i_%<dciId>_%<instance>','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (21,'8e26af4b-e478-44c9-9c12-0b049ccb6c3c',7944,'Terminate table threshold violation alarms',
  '%m',6,'DCTTHR_%i_%<dciId>_%<instance>','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (22,'ea1dee96-b42e-499c-a992-0b0f9e4874b9',7944,'Generate an alarm when one of the system threads hangs or stops unexpectedly',
        '%m',5,'SYS_THREAD_HANG_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (23,'f0c5a6b2-7427-45e5-8333-7d60d2b408e6',7944,'Terminate the alarm when one of the system threads which previously hanged or stoped unexpectedly returned to the running state',
        '%m',6,'SYS_THREAD_HANG_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (24,'ed3397a8-a496-4534-839b-5a6fc77c167c',7944,'Generate an alarm when the object enters the maintanance mode',
        '%m',5,'MAINTENANCE_MODE_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
 VALUES (25,'20a0f4a5-d90e-4961-ba88-a65b9ee45d07',7944,'Terminate the alarm when the object leaves the maintanance mode',
        '%m',6,'MAINTENANCE_MODE_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (26,'c6f66840-4758-420f-a27d-7bbf7b66a511',7944,'Generate an alarm if the NetXMS agent on the node stops responding',
        '%m',5,'AGENT_UNREACHABLE_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (27,'9fa60260-c2ec-4371-b4e4-f5346b1d8400',7944,'Terminate the alarm if the NetXMS agent on the node start responding again',
        '%m',6,'AGENT_UNREACHABLE_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (28,'20ef861f-b8e4-4e04-898e-e57d13863661',7944,'Generate an alarm if the SNMP agent on the node stops responding',
        '%m',5,'SNMP_UNREACHABLE_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (29,'33f6193a-e103-4f5f-8bee-870bbcc08066',7944,'Terminate the alarm if the SNMP agent on the node start responding again',
        '%m',6,'SNMP_UNREACHABLE_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (30,'417648af-5361-49a5-9471-6ef31e857b2d',7944,'Generate an alarm when error occurred during LDAP synchronization',
        '%m',5,'SYS_LDAP_SYNC_ERROR_%2','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (31,'19bd89ba-8bb2-4915-8546-a1ecc650dedd',7944,'Generate an alarm when there is problem with agent log',
        '%m',5,'SYS_AGENT_LOG_PROBLEM_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (32,'cff7fe6b-2ad1-4c18-8a8f-4d397d44fe04',7944,'Generate an alarm when there is problem with agent local database',
        '%m',5,'SYS_AGENT_LOCAL_DATABASE_PROBLEM_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (33,'68e102a3-58d3-4112-901c-f683356ba662',7944,'Show alarm when interface with expected state UP is administratively disabled',
        '%m',2,'IF_DOWN_%i_%5',
        'interface = GetInterfaceObject($node, $event->parameters[5]);' || chr(13)||chr(10) || chr(13)||chr(10) ||
        '// if interface not found or interface expected state is not UP, do not match' || chr(13)||chr(10) ||
        '// otherwise (if interface expected status is UP), match' || chr(13)||chr(10) ||
        'return (interface != null && interface->expectedState == 0);',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (34,'68a629ef-c645-49e5-8a7b-c5e79308080e',7944,'Generate alarm when MAC address change detected on interface',
        '%m',1,'MAC_ADDRESS_CHANGED_%i_%2','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (35,'18507220-6a16-4e13-a6ba-141d907b924a',7944,'Terminate MAC address change alarms when the interface is deleted',
        '%m',6,'MAC_ADDRESS_CHANGED_%i_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (36,'8cca36cd-f821-43ae-8329-0eefe35df3b7',7944,'Terminate network mask alarms when an interface is deleted',
        '%m',6,'BAD_NETMASK_%i_%1','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (37,'404642a0-deb0-400d-b9f9-c86f5a83f7f5',7944,'Terminate network mask alarms when the mask of interface changes or IP address of interface is deleted',
        '%m',6,'BAD_NETMASK_%i_%5','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (38,'b8abb037-ab4a-4e05-adc4-9425ce440e4a',7944,'Generate alarm when a routing loop is detected',
        '%m',5,'ROUTING_LOOP_%i','',0,43);
INSERT INTO event_policy (rule_id,rule_guid,flags,comments,alarm_message,alarm_severity,alarm_key,script,alarm_timeout,alarm_timeout_event)
    VALUES (39,'2bb3df47-482b-4e4b-9b49-8c72c6b33011',7944,'Generate alarm on software package changes',
        '%m',5,'SW_PKG_%i_%<name>','',0,43);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (0,28);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (1,29);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (2,25);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (2,27);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (3,26);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (4,5);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (5,4);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (6,16);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (6,84);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (6,85);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (7,62);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (8,63);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (9,16);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (9,83);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (9,85);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (10,24);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (11,33);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (12,36);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (13,37);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (14,50);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (15,51);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (16,54);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (16,53);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (17,55);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (18,17);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (19,18);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (20,69);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (21,70);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (22,20);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (23,21);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (24,78);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (25,79);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (26,15);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (27,32);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (28,14);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (29,31);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (30,80);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (31,81);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (32,82);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (33,46);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (34,23);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (35,16);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (36,16);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (37,75);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (37,77);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (38,86);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (39,87);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (39,88);
INSERT INTO policy_event_list (rule_id,event_code) VALUES (39,89);
INSERT INTO snmp_trap_cfg (guid,trap_id,snmp_oid,event_code,user_tag,description)
   VALUES ('5d01e7e5-edbb-46ce-b53c-f7f64d1bf8ff',1,'.1.3.6.1.6.3.1.1.5.1',501,'','Generic coldStart trap');
INSERT INTO snmp_trap_cfg (guid,trap_id,snmp_oid,event_code,user_tag,description)
   VALUES ('c5464919-fd76-4624-9c21-b6ab73d9df80',2,'.1.3.6.1.6.3.1.1.5.2',502,'','Generic warmStart trap');
INSERT INTO snmp_trap_cfg (guid,trap_id,snmp_oid,event_code,user_tag,description)
   VALUES ('44d3b32e-33c5-4a39-b2ad-990a1120155d',3,'.1.3.6.1.6.3.1.1.5.3',503,'','Generic linkDown trap');
INSERT INTO snmp_trap_cfg (guid,trap_id,snmp_oid,event_code,user_tag,description)
   VALUES ('c9660f48-a4b3-41c8-b3f9-e9a6a8129db5',4,'.1.3.6.1.6.3.1.1.5.4',504,'','Generic linkUp trap');
INSERT INTO snmp_trap_cfg (guid,trap_id,snmp_oid,event_code,user_tag,description)
   VALUES ('4b422ba6-4b45-4881-931a-ed38dc798f9f',5,'.1.3.6.1.6.3.1.1.5.5',505,'','Generic authenticationFailure trap');
INSERT INTO snmp_trap_cfg (guid,trap_id,snmp_oid,event_code,user_tag,description)
   VALUES ('bd8b6971-a3e4-4cad-9c70-3a33e61e0913',6,'.1.3.6.1.6.3.1.1.5.6',506,'','Generic egpNeighborLoss trap');
INSERT INTO snmp_trap_pmap (trap_id,parameter,flags,snmp_oid,description)
   VALUES (3,1,0,'.1.3.6.1.2.1.2.2.1.1','Interface index');
INSERT INTO snmp_trap_pmap (trap_id,parameter,flags,snmp_oid,description)
   VALUES (4,1,0,'.1.3.6.1.2.1.2.2.1.1','Interface index');
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text,command_name,command_short_name,icon)
 VALUES (1,'7373a74b-e805-1a49-b52c-19410c1c347f','&Shutdown system',1,'System.Shutdown',9,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Shutdown target node via NetXMS agent',
           'Host %n (%a) will be shut down. Are you sure?', 'Shutdown system','Shutdown',
           '89504e470d0a1a0a0000000d49484452000000100000001008060000001ff3ff610000000473424954080808087c086488000002bf49444154388d95933f689c7518c73fbf3f7779efaede995c137369d54123ba4843070b1204c10ed24db182939b90c1c1e2eee05871e8d0d2b98a8ba0c52ee2d0cb622acd99222dd1084d9a18cd3597f7bd6bdebbdffbfbe7701d82e8e0071ebec303dfe7e1e1fb883fea7542aba54c080dac153c261ed1a30540b51a8f359bd9c9e565afedd4943ab6b4f4d56873f38d5014ff6af04f95950aaad5fa7e7d7dfdbcee1d1c3cd95d59395b5a5c7c82ffc1fd4ee7acc9f349fd683090b1db15499ae2013b3f8f1c0e296f6f539c380140796707333747a856296d6ca081d1e1a138cc73a95d8cc28f468834459f3ecd7367cee0b38ccd7bf7787e711180dfaf5ee599850544a3c1760898d5556c51e06314d2c5288be150186b995d58404bc9eef5ebb87e86140229257690b17be33b4a4a3173ea14236b71d60a17a3901684b59652b34952ab31dcda6470f76794c9b0b6c0160665320eefae317ab04552ad529e9ec6c78003292dc861bf2f4408e369fb7b948a8cb2cd7085c115868998936887eb75514a617a3db66eb68505211d30f86b97dde536420844a341b17e8bf8db0a21ed12d23ddcda0ff46f7e4dac24482939b8b386b3060f4207206a457afb16be9f519f7f91f22baf52f9e91bfca7ef00829a4fb1af9fa3fed2cbf8419f6c75054a0a0fc800a025f151cafdcb17514af3ecc79f939fbf40d69c259d9ca1ffd687cc7d7411a5145b573e230e52d0120f68ffd8400ad8b97685c9934f31f9ee07b4de5e227ff37d8c311c4f12aad50afb5f5c62e7da65a400519204408f37108408de471e5cfa04fbe3b74c9d7b8ff2d32f1042805f7e25bdf1257fdeee103c8408528d53afa356c85a42b107d6812920bdd3c16f7448cae3d81a0b837cdc2b1c380f724203445d8ff161767cb66df1afe5380a0d3d05ca8d0f148110c02bb035b013109b1a17747b06baa20d3c84897dc93420feeb0b8f22203603dd19307f037f0665861328b32e0000000049454e44ae426082'
           );
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text,command_name,command_short_name,icon)
 VALUES (2,'b1e6192c-ff80-c842-a532-c60e40cb9fa4','&Restart system',1,'System.Restart',9,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Restart target node via NetXMS agent',
           'Host %n (%a) will be restarted. Are you sure?', 'Restart system','Restart',
           '89504e470d0a1a0a0000000d49484452000000100000001008060000001ff3ff610000000473424954080808087c0864880000029849444154388d85934d8b1c5514869f73eeadaaae6ea77bda388999882241fc5c0e09ae4484c4857b4177fa0b5474256edcfa075cbacbc23f6074a12332a0a2200a2189e8c468a23d93e99e9eaeaaae7beb1e1733f9902c7ce06c0ebc2f2fe7f0ca5fe311fdd71e77d93332922e08b749dcc5fe3bc9f72d5d1fcf861f7dd6f9f295353778ebdd0b71ff977374ad60f7888e1003bbb3379ceb930f4e7fbe7be5a7573da7f65697db17cf2ff2e757fe6e06a00e1141040aab59ebb5dc47076efbdb739cacc63edc9aabee4f64796248efb10dbcf738e750556268e97eff14b937ce6d1607126e55eae33c4956d5f4f72f21ae40d6cfe0bc4755c9f39cd4ccc0d27d7a695ae23c896fe7a645d5482c26340f0d19e639beb9811463c85788610a29c1d11d4284cc416a82848589861a49754bab390fac3f4ba69174f963ba7040d745249f4136033f63efd859769f78933a792c244265ea436d9a9a99e86895bc28b0e90fc03632bd88e463acb787580696f3e0fa299ade09e275a5feed2b09b5898f4ba35bdc40bb6b0034f5357cda4277bec354400d0b0d5d75406a5e42caa751d90596c425e22d00aa48771933a3c99e6230a8d1b241dcd1eb03a4a2c4563600f07615bc622da80510149aefa1b982ef3dc24d7d071b7afc71f0c781d58c83d107e48347d1f62a1a7f44f4d0c0130c4c4196d89fefa1273f215f7d9d4b379fa4dfdf22cb32bc7f99b5f533c45893edbc4fe75a8c0116c05b008b408434fd9a327f031b7d08c73670ee2c65595296259afe20fbe76de2fc9ba39e1cd6c6a7e4b0ae87d5200a4cbea4acce3318bd8865cfe1a283dd9fe9a65f901615a982d400c96360be9eda4ebbfdf0a6ec752f741ac11f1a89db02d9ba5bc8d483ae877587e2f0abdfac2b26b209488fa218b07627d7ff636dc524d52cff0513e53f37235ac3190000000049454e44ae426082'
           );
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text,command_name,command_short_name,icon)
 VALUES (3,'74cc4d38-9239-7b4f-9905-7f7adc4d489a','&Wakeup node',0,'wakeup',8,'','Wakeup node using Wake-On-LAN magic packet','','Wakeup node using Wake-On-LAN','Wakeup',
           '89504e470d0a1a0a0000000d49484452000000100000001008060000001ff3ff610000000473424954080808087c086488000000097048597300000dd700000dd70142289b780000001974455874536f667477617265007777772e696e6b73636170652e6f72679bee3c1a0000023649444154388d8d924f48545114c67ff7bd37ff7cf9071bc70a4d47271ca15c848d448185b40a89204890362d8568eb2270d7a2762d5cb7711504d1ae10c195218895a488528e4e06a653d338de7bdfbcf75ac84ce38c901f9ccdb9e7fbce39f77c627ce6872df6dd71f01f781e1d9c00866003215efaf99de7d6763afb1078721262053a800908ed5a5aa9b1e3bb0802a600c0717d3cdf3fae6cccd24a25abb302a80b990c265a009859d941299763249296d6b2a6732468d25a1f24156f00e0cbd62e9b5a71a0dd9a490cad14a570b4266c780cf546797cab1b1317139747435ddcec69266c78385a53c9b1b45265b548d022d51563f45a9c778b69ce35850058de928c0cb4933fd04c7ffece812e9639e5158480865098ebc9181fbfeef07a6e9dc68805c0af8243f45480ab174e33bb9426e7484a9b942710020c3b40e24c236f3facb1bd9b634d3a00d8e100ab992cb7af7421bc225aa9b280a195a414524972054d5f679488e5a394442949d8f4b8d4d14caea09115f55a490cad155a2b9452ecfdcef37e619ddef6287706ba89c76ce2319be1fe4e926d51663e6d90cdeda3d42147ebaa4fcc161da6a61739df52cfe88d8b0ca712f8be871d0e31bb94666a7a916c2e8feb7aff3cd33ef2f4c8612dd3a0a5d1a6bfa78d544f1bbeef33bf9a617e65939fb902c50a328068bd3bb10c1c71a3210401cb24143cbc82d2459c62ad8980154b2b3909bca87e91c09fea642d26ad67f7fb32afe6bebd5958dd1c2c48ddf45f8a10d87591bdcb89b3b3f7063a337f01f30f1c1c580292640000000049454e44ae426082'
           );
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (4,'1223b7fe-2098-2a4d-8618-67bdb66a907e','Restart &agent',1,'Agent.Restart',1,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Restart NetXMS agent on target node',
           'NetXMS agent on host %n (%a) will be restarted. Are you sure?');
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (5,'adc5168f-7e0b-f449-a876-cc4720d1a29a','&Info->&Switch forwarding database (FDB)',2,'Forwarding database',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show switch forwarding database','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (5,0,'MAC Address','.1.3.6.1.2.1.17.4.3.1.1',4,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (5,1,'Port','.1.3.6.1.2.1.17.4.3.1.2',5,0);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (6,'fa403c91-28d6-2b49-a96b-fd4d688c5862','&Connect->Open &web browser',4,'http://%a',0,'','Open embedded web browser to node','');
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (7,'a4d9c827-916b-2640-b6e2-d5bf512c39d7','&Connect->Open &web browser (HTTPS)',4,'https://%a',0,'','Open embedded web browser to node using HTTPS','');
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (8,'ded1f018-9659-c548-8adb-4af5c759c7ba','&Info->&Agent->&Subagent list',3,'Subagent ListAgent.SubAgentList^(.*) (.*) (.*) (.*)',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of loaded subagents','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (8,0,'Name','',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (8,1,'Version','',0,2);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (8,2,'File','',0,4);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (8,3,'Module handle','',0,3);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (9,'cf606f35-ea88-2446-bdf5-366799e615c9','&Info->&Agent->Supported &parameters',3,'Supported parametersAgent.SupportedParameters^(.*)',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of parameters supported by agent','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (9,0,'Parameter','',0,1);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (10,'84c8b119-fda0-5d4f-b480-75229315ce20','&Info->&Agent->Supported &lists',3,'Supported listsAgent.SupportedLists^(.*)',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of lists supported by agent','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (10,0,'Parameter','',0,1);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (11,'d6ea7dcf-c342-b04e-a91b-566beda73c4d','&Info->&Agent->Supported &actions',3,'Supported actionsAgent.ActionList^(.*) (.*) "(.*)".*',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of actions supported by agent','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (11,0,'Name','',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (11,1,'Type','',0,2);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (11,2,'Data','',0,3);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (12,'780a1b84-e600-a748-89af-c5e02e5b8cfc','&Info->&Agent->Configured &ICMP targets',3,'Configured ICMP targetsICMP.TargetList^(.*) (.*) (.*) (.*) (.*)',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of actions supported by agent','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (12,0,'IP Address','',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (12,1,'Name','',0,5);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (12,2,'Packet size','',0,4);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (12,3,'Last RTT','',0,2);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (12,4,'Average RTT','',0,3);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (13,'7afea6f9-ccd2-1d45-9e8c-3aafe14cb565','&Info->&Process list',3,
           'Process ListSystem.ProcessList^([0-9]+) (.*)',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of currently running processes','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (13,0,'PID','',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (13,1,'Name','',0,2);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (14,'80f3c244-87b1-954e-99e1-3c94062a2cc7','&Info->Topology table (Nortel)',2,'Topology table',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show topology table (Nortel protocol)','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (14,0,'Peer IP','.1.3.6.1.4.1.45.1.6.13.2.1.1.3',3,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (14,1,'Peer MAC','.1.3.6.1.4.1.45.1.6.13.2.1.1.5',4,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (14,2,'Slot','.1.3.6.1.4.1.45.1.6.13.2.1.1.1',1,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (14,3,'Port','.1.3.6.1.4.1.45.1.6.13.2.1.1.2',1,0);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (15,'e7f69f34-7956-674b-aa5f-aa6d07e3bba3','&Info->Topology table (CDP)',2,'Topology table',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show topology table (CDP)','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (15,0,'Device ID','.1.3.6.1.4.1.9.9.23.1.2.1.1.6',0,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (15,1,'IP Address','.1.3.6.1.4.1.9.9.23.1.2.1.1.4',3,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (15,2,'Platform','.1.3.6.1.4.1.9.9.23.1.2.1.1.8',0,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (15,3,'Version','.1.3.6.1.4.1.9.9.23.1.2.1.1.5',0,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (15,4,'Port','.1.3.6.1.4.1.9.9.23.1.2.1.1.7',0,0);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (16,'87370de4-e760-904a-9f9d-dfdf15f4dd9e','&Info->Active &user sessions',3,
           'Active User SessionsSystem.ActiveUserSessions^"(.*)" "(.*)" "(.*)"',0,'<objectMenuFilter><flags>2</flags></objectMenuFilter>','Show list of active user sessions','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (16,0,'User','',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (16,1,'Terminal','',0,2);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (16,2,'From','',0,3);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (17,'81a0f545-3dbf-6c47-acfc-43ae88465360','&Info->AR&P cache (SNMP)',2,'ARP Cache',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show ARP cache','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (17,0,'IP Address','.1.3.6.1.2.1.4.22.1.3',3,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (17,1,'MAC Address','.1.3.6.1.2.1.4.22.1.2',4,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (17,2,'Interface','.1.3.6.1.2.1.4.22.1.1',5,0);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (18,'86b9772e-b939-1849-bc5b-d2c1a170ec96','&Info->AR&P cache (Agent)',3,
           'ARP CacheNet.ArpCache(.*) (.*) (.*)',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show ARP cache','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (18,0,'IP Address','.1.3.6.1.2.1.4.22.1.3',0,2);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (18,1,'MAC Address','.1.3.6.1.2.1.4.22.1.2',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (18,2,'Interface','.1.3.6.1.2.1.4.22.1.1',5,3);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (19,'565bb6e4-cee3-4741-82d6-0f379c742c55','&Info->&Routing table (SNMP)',2,'Routing Table',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show IP routing table','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (19,0,'Destination','.1.3.6.1.2.1.4.21.1.1',3,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (19,1,'Mask','.1.3.6.1.2.1.4.21.1.11',3,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (19,2,'Next hop','.1.3.6.1.2.1.4.21.1.7',3,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (19,3,'Metric','.1.3.6.1.2.1.4.21.1.3',1,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (19,4,'Interface','.1.3.6.1.2.1.4.21.1.2',5,0);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,flags,tool_filter,description,confirmation_text)
 VALUES (20,'83eeea21-dd61-f84e-811d-3861ccee08ed','&Info->Topology table (LLDP)',2,'Topology Table',0,'<objectMenuFilter><flags>1</flags></objectMenuFilter>','Show topology table (LLDP)','');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (20,0,'Chassis ID','.1.0.8802.1.1.2.1.4.1.1.5',0,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (20,1,'Local port','.1.0.8802.1.1.2.1.4.1.1.2',5,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (20,2,'System name','.1.0.8802.1.1.2.1.4.1.1.9',0,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (20,3,'System description','.1.0.8802.1.1.2.1.4.1.1.10',0,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (20,4,'Remote port ID','.1.0.8802.1.1.2.1.4.1.1.7',4,0);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (20,5,'Remote port description','.1.0.8802.1.1.2.1.4.1.1.8',0,0);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,description,flags,tool_filter)
 VALUES (21,'9b011dcc-a62a-c945-87c9-d12ea7b034ac','QtechOLTsONT',2,'','Show ONTs on PON ports',0,'<objectMenuFilter><toolOS></toolOS><toolTemplate></toolTemplate><snmpOid>.1.3.6.1.4.1.27514.1.10.4.1</snmpOid><flags>5</flags></objectMenuFilter>');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,0,'Num','.1.3.6.1.4.1.27514.1.11.4.1.1.1',1,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,1,'SN','.1.3.6.1.4.1.27514.1.11.4.1.1.2',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,2,'FW','.1.3.6.1.4.1.27514.1.11.4.1.1.12',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,3,'Description','.1.3.6.1.4.1.27514.1.11.4.1.1.13',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,4,'Uptime','.1.3.6.1.4.1.27514.1.11.4.1.1.19',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,5,'Distance','.1.3.6.1.4.1.27514.1.11.4.1.1.32',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,6,'RSSI','.1.3.6.1.4.1.27514.1.11.4.1.1.22',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,7,'Temp','.1.3.6.1.4.1.27514.1.11.4.1.1.24',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (21,8,'Status','.1.3.6.1.4.1.27514.1.11.4.1.1.3',0,1);
INSERT INTO object_tools (tool_id,guid,tool_name,tool_type,tool_data,description,flags,tool_filter)
 VALUES (22,'4bbcd005-18ab-4b4d-ad57-d978ced60d4f','QtechOLTsSFP',2,'','Show GPON SFP modules in PON ports',0,'<objectMenuFilter><toolOS></toolOS><toolTemplate></toolTemplate><snmpOid>.1.3.6.1.4.1.27514.1.10.4.1</snmpOid><flags>5</flags></objectMenuFilter>');
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,0,'Num','.1.3.6.1.4.1.27514.1.11.3.1.1.1.0',5,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,1,'Presence','.1.3.6.1.4.1.27514.1.11.3.1.1.15.0',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,2,'SN','.1.3.6.1.4.1.27514.1.11.3.1.1.16.0',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,3,'Distance','.1.3.6.1.4.1.27514.1.11.3.1.1.14.0',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,4,'Manufacturer','.1.3.6.1.4.1.27514.1.11.3.1.1.18.0',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,5,'Temp','.1.3.6.1.4.1.27514.1.11.3.1.1.19.0',0,1);
INSERT INTO object_tools_table_columns (tool_id,col_number,col_name,col_oid,col_format,col_substr)
 VALUES (22,6,'ManufacturedDate','.1.3.6.1.4.1.27514.1.11.3.1.1.17.0',0,1);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (1,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (2,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (3,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (4,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (5,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (6,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (7,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (8,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (9,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (10,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (11,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (12,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (13,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (14,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (15,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (16,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (17,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (18,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (19,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (20,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (21,-2147483648);
INSERT INTO object_tools_acl (tool_id,user_id) VALUES (22,-2147483648);
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('3b7bddce-3505-42ff-ac60-6a48a64bd0ae',1,'Filter::SNMP','return $1->isSNMP;' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('2fb9212b-97e6-40e7-b434-2df4f7e8f6aa',2,'Filter::Agent','return $1->isAgent;' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('38696a00-c519-438c-8cbd-4b3a0cba4af1',3,'Filter::AgentOrSNMP','return $1->isAgent || $1->isSNMP;' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('efe50915-47b2-43d8-b4f4-2c09a44970c3',4,'DCI::SampleTransform','sub dci_transform()' || chr(13)||chr(10) || '{' || chr(13)||chr(10) || '   return $1 + 1;' || chr(13)||chr(10) || '}' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('7837580c-4054-40f2-981f-7185797fe7d7',11,'Hook::StatusPoll','/* Available global variables:' || chr(13)||chr(10) || ' *  $node - current node, object of ''Node'' type' || chr(13)||chr(10) || ' *' || chr(13)||chr(10) || ' * Expected return value:' || chr(13)||chr(10) || ' *  none - returned value is ignored' || chr(13)||chr(10) || ' */' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('f7d1bc7e-4046-4ee4-adb2-718f7361984d',12,'Hook::ConfigurationPoll','/* Available global variables:' || chr(13)||chr(10) || ' *  $node - current node, object of ''Node'' type' || chr(13)||chr(10) || ' *' || chr(13)||chr(10) || ' * Expected return value:' || chr(13)||chr(10) || ' *  none - returned value is ignored' || chr(13)||chr(10) || ' */' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('048fcf32-765b-4702-9c70-f012f62d5a90',13,'Hook::InstancePoll','/* Available global variables:' || chr(13)||chr(10) || ' *  $node - current node, object of ''Node'' type' || chr(13)||chr(10) || ' *' || chr(13)||chr(10) || ' * Expected return value:' || chr(13)||chr(10) || ' *  none - returned value is ignored' || chr(13)||chr(10) || ' */' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('d515c10f-a5c9-4f41-afcd-9ddc8845f288',14,'Hook::TopologyPoll','/* Available global variables:' || chr(13)||chr(10) || ' *  $node - current node, object of ''Node'' type' || chr(13)||chr(10) || ' *' || chr(13)||chr(10) || ' * Expected return value:' || chr(13)||chr(10) || ' *  none - returned value is ignored' || chr(13)||chr(10) || ' */' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('7cd1c471-2f14-4fae-8743-8899fed64d18',15,'Hook::CreateInterface','/* Available global variables:' || chr(13)||chr(10) || ' *  $node - current node, object of ''Node'' type' || chr(13)||chr(10) || ' *  $1 - current interface, object of ''Interface'' type' || chr(13)||chr(10) || ' *' || chr(13)||chr(10) || ' * Expected return value:' || chr(13)||chr(10) || ' *  true/false - boolean - whether interface should be created' || chr(13)||chr(10) || ' */' || chr(13)||chr(10) || 'return true;' || chr(13)||chr(10));
INSERT INTO script_library (guid,script_id,script_name,script_code)
 VALUES ('befdb083-ac68-481d-a7b7-127e11c3fae0',16,'Hook::AcceptNewNode','/* Available global variables:' || chr(13)||chr(10) || ' *  $ipAddr - IP address of the node being processed' || chr(13)||chr(10) || ' *  $ipNetMask - netmask of the node being processed' || chr(13)||chr(10) || ' *  $macAddr - MAC address of the node being processed' || chr(13)||chr(10) || ' *  $zoneId - zone ID of the node being processed' || chr(13)||chr(10) || ' *' || chr(13)||chr(10) || ' * Expected return value:' || chr(13)||chr(10) || ' *  true/false - boolean - whether node should be created' || chr(13)||chr(10) || ' */' || chr(13)||chr(10) || 'return true;' || chr(13)||chr(10));
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('092e4b35-4e7c-42df-b9b7-d5805bfac64e', 'Service', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('1ddb76a3-a05f-4a42-acda-22021768feaf', 'ATM', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('7cd999e9-fbe0-45c3-a695-f84523b3a50c', 'Unknown', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('904e7291-ee3f-41b7-8132-2bd29288ecc8', 'Node', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('b314cf44-b2aa-478e-b23a-73bc5bb9a624', 'HSM', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('ba6ab507-f62d-4b8f-824c-ca9d46f22375', 'Server', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('bacde727-b183-4e6c-8dca-ab024c88b999', 'Router', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('f5214d16-1ab1-4577-bb21-063cfd45d7af', 'Printer', 'Network Objects', 'image/png', 1);
INSERT INTO images (guid, name, category, mimetype, protected) VALUES
  ('f9105c54-8dcf-483a-b387-b4587dfd3cba', 'Switch', 'Network Objects', 'image/png', 1);
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AD','AND','020','Andorra');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AE','ARE','784','United Arab Emirates');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AF','AFG','004','Afghanistan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AG','ATG','028','Antigua and Barbuda');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AI','AIA','660','Anguilla');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AL','ALB','008','Albania');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AM','ARM','051','Armenia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AN','ANT','530','Netherlands Antilles');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AO','AGO','024','Angola');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AQ','ATA','010','Antarctica');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AR','ARG','032','Argentina');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AS','ASM','016','American Samoa');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AT','AUT','040','Austria');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AU','AUS','036','Australia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AW','ABW','533','Aruba');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AX','ALA','248','Aland Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('AZ','AZE','031','Azerbaijan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BA','BIH','070','Bosnia and Herzegovina');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BB','BRB','052','Barbados');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BD','BGD','050','Bangladesh');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BE','BEL','056','Belgium');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BF','BFA','854','Burkina Faso');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BG','BGR','100','Bulgaria');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BH','BHR','048','Bahrain');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BI','BDI','108','Burundi');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BJ','BEN','204','Benin');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BL','BLM','652','Saint-Barthelemy');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BM','BMU','060','Bermuda');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BN','BRN','096','Brunei Darussalam');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BO','BOL','068','Bolivia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BQ','BES','535','Bonaire, Sint Eustatius and Saba');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BR','BRA','076','Brazil');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BS','BHS','044','Bahamas');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BT','BTN','064','Bhutan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BV','BVT','074','Bouvet Island');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BW','BWA','072','Botswana');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BY','BLR','112','Belarus');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('BZ','BLZ','084','Belize');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CA','CAN','124','Canada');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CC','CCK','166','Cocos (Keeling) Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CD','COD','180','Congo, Democratic Republic of the');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CF','CAF','140','Central African Republic');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CG','COG','178','Congo (Brazzaville)');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CH','CHE','756','Switzerland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CI','CIV','384','Cote d''Ivoire');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CK','COK','184','Cook Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CL','CHL','152','Chile');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CM','CMR','120','Cameroon');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CN','CHN','156','China');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CO','COL','170','Colombia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CR','CRI','188','Costa Rica');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CU','CUB','192','Cuba');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CV','CPV','132','Cape Verde');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CW','CUW','531','Curacao');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CX','CXR','162','Christmas Island');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CY','CYP','196','Cyprus');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('CZ','CZE','203','Czech Republic');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('DE','DEU','276','Germany');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('DJ','DJI','262','Djibouti');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('DK','DNK','208','Denmark');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('DM','DMA','212','Dominica');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('DO','DOM','214','Dominican Republic');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('DZ','DZA','012','Algeria');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('EC','ECU','218','Ecuador');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('EE','EST','233','Estonia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('EG','EGY','818','Egypt');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('EH','ESH','732','Western Sahara');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ER','ERI','232','Eritrea');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ES','ESP','724','Spain');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ET','ETH','231','Ethiopia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('FI','FIN','246','Finland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('FJ','FJI','242','Fiji');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('FK','FLK','238','Falkland Islands (Malvinas)');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('FM','FSM','583','Micronesia, Federated States of');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('FO','FRO','234','Faroe Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('FR','FRA','250','France');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GA','GAB','266','Gabon');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GB','GBR','826','United Kingdom');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GD','GRD','308','Grenada');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GE','GEO','268','Georgia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GF','GUF','254','French Guiana');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GG','GGY','831','Guernsey');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GH','GHA','288','Ghana');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GI','GIB','292','Gibraltar');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GL','GRL','304','Greenland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GM','GMB','270','Gambia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GN','GIN','324','Guinea');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GP','GLP','312','Guadeloupe');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GQ','GNQ','226','Equatorial Guinea');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GR','GRC','300','Greece');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GS','SGS','239','South Georgia and the South Sandwich Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GT','GTM','320','Guatemala');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GU','GUM','316','Guam');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GW','GNB','624','Guinea-Bissau');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('GY','GUY','328','Guyana');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('HK','HKG','344','Hong Kong, Special Administrative Region of China');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('HM','HMD','334','Heard Island and Mcdonald Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('HN','HND','340','Honduras');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('HR','HRV','191','Croatia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('HT','HTI','332','Haiti');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('HU','HUN','348','Hungary');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ID','IDN','360','Indonesia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IE','IRL','372','Ireland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IL','ISR','376','Israel');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IM','IMN','833','Isle of Man');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IN','IND','356','India');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IO','IOT','086','British Indian Ocean Territory');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IQ','IRQ','368','Iraq');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IR','IRN','364','Iran, Islamic Republic of');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IS','ISL','352','Iceland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('IT','ITA','380','Italy');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('JE','JEY','832','Jersey');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('JM','JAM','388','Jamaica');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('JO','JOR','400','Jordan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('JP','JPN','392','Japan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KE','KEN','404','Kenya');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KG','KGZ','417','Kyrgyzstan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KH','KHM','116','Cambodia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KI','KIR','296','Kiribati');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KM','COM','174','Comoros');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KN','KNA','659','Saint Kitts and Nevis');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KP','PRK','408','Korea, Democratic People''s Republic of');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KR','KOR','410','Korea, Republic of');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KW','KWT','414','Kuwait');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KY','CYM','136','Cayman Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('KZ','KAZ','398','Kazakhstan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LA','LAO','418','Lao PDR');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LB','LBN','422','Lebanon');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LC','LCA','662','Saint Lucia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LI','LIE','438','Liechtenstein');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LK','LKA','144','Sri Lanka');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LR','LBR','430','Liberia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LS','LSO','426','Lesotho');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LT','LTU','440','Lithuania');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LU','LUX','442','Luxembourg');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LV','LVA','428','Latvia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('LY','LBY','434','Libya');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MA','MAR','504','Morocco');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MC','MCO','492','Monaco');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MD','MDA','498','Moldova');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ME','MNE','499','Montenegro');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MF','MAF','663','Saint-Martin (French part)');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MG','MDG','450','Madagascar');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MH','MHL','584','Marshall Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MK','MKD','807','Macedonia, Republic of');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ML','MLI','466','Mali');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MM','MMR','104','Myanmar');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MN','MNG','496','Mongolia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MO','MAC','446','Macao, Special Administrative Region of China');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MP','MNP','580','Northern Mariana Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MQ','MTQ','474','Martinique');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MR','MRT','478','Mauritania');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MS','MSR','500','Montserrat');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MT','MLT','470','Malta');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MU','MUS','480','Mauritius');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MV','MDV','462','Maldives');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MW','MWI','454','Malawi');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MX','MEX','484','Mexico');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MY','MYS','458','Malaysia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('MZ','MOZ','508','Mozambique');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NA','NAM','516','Namibia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NC','NCL','540','New Caledonia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NE','NER','562','Niger');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NF','NFK','574','Norfolk Island');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NG','NGA','566','Nigeria');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NI','NIC','558','Nicaragua');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NL','NLD','528','Netherlands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NO','NOR','578','Norway');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NP','NPL','524','Nepal');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NR','NRU','520','Nauru');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NU','NIU','570','Niue');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('NZ','NZL','554','New Zealand');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('OM','OMN','512','Oman');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PA','PAN','591','Panama');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PE','PER','604','Peru');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PF','PYF','258','French Polynesia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PG','PNG','598','Papua New Guinea');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PH','PHL','608','Philippines');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PK','PAK','586','Pakistan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PL','POL','616','Poland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PM','SPM','666','Saint Pierre and Miquelon');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PN','PCN','612','Pitcairn');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PR','PRI','630','Puerto Rico');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PS','PSE','275','Palestinian Territory, Occupied');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PT','PRT','620','Portugal');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PW','PLW','585','Palau');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('PY','PRY','600','Paraguay');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('QA','QAT','634','Qatar');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('RE','REU','638','Reunion');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('RO','ROU','642','Romania');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('RS','SRB','688','Serbia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('RU','RUS','643','Russian Federation');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('RW','RWA','646','Rwanda');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SA','SAU','682','Saudi Arabia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SB','SLB','090','Solomon Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SC','SYC','690','Seychelles');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SD','SDN','729','Sudan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SE','SWE','752','Sweden');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SG','SGP','702','Singapore');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SH','SHN','654','Saint Helena');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SI','SVN','705','Slovenia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SJ','SJM','744','Svalbard and Jan Mayen Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SK','SVK','703','Slovakia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SL','SLE','694','Sierra Leone');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SM','SMR','674','San Marino');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SN','SEN','686','Senegal');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SO','SOM','706','Somalia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SR','SUR','740','Suriname');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SS','SSD','728','South Sudan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ST','STP','678','Sao Tome and Principe');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SV','SLV','222','El Salvador');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SX','SXM','534','Sint Maarten (Dutch part)');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SY','SYR','760','Syrian Arab Republic (Syria)');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('SZ','SWZ','748','Swaziland');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TC','TCA','796','Turks and Caicos Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TD','TCD','148','Chad');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TF','ATF','260','French Southern Territories');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TG','TGO','768','Togo');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TH','THA','764','Thailand');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TJ','TJK','762','Tajikistan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TK','TKL','772','Tokelau');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TL','TLS','626','Timor-Leste');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TM','TKM','795','Turkmenistan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TN','TUN','788','Tunisia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TO','TON','776','Tonga');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TR','TUR','792','Turkey');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TT','TTO','780','Trinidad and Tobago');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TV','TUV','798','Tuvalu');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TW','TWN','158','Taiwan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('TZ','TZA','834','Tanzania');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('UA','UKR','804','Ukraine');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('UG','UGA','800','Uganda');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('UM','UMI','581','United States Minor Outlying Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('US','USA','840','United States of America');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('UY','URY','858','Uruguay');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('UZ','UZB','860','Uzbekistan');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VA','VAT','336','Holy See (Vatican City State)');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VC','VCT','670','Saint Vincent and Grenadines');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VE','VEN','862','Venezuela');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VG','VGB','092','British Virgin Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VI','VIR','850','Virgin Islands, US');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VN','VNM','704','Viet Nam');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('VU','VUT','548','Vanuatu');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('WF','WLF','876','Wallis and Futuna Islands');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('WS','WSM','882','Samoa');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('YE','YEM','887','Yemen');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('YT','MYT','175','Mayotte');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ZA','ZAF','710','South Africa');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ZM','ZMB','894','Zambia');
INSERT INTO country_codes (alpha_code,alpha3_code,numeric_code,name) VALUES ('ZW','ZWE','716','Zimbabwe');
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('008', 'ALL', 'Lek', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('012', 'DZD', 'Algerian Dinar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('031', 'AZM', 'Azerbaijanian Manat', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('032', 'ARS', 'Argentine Peso', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('036', 'AUD', 'Australian Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('044', 'BSD', 'Bahamian Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('048', 'BHD', 'Bahraini Dinar', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('050', 'BDT', 'Taka', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('051', 'AMD', 'Armenian Dram', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('052', 'BBD', 'Barbados Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('060', 'BMD', 'Bermudian Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('064', 'BTN', 'Ngultrum', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('068', 'BOB', 'Boliviano', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('072', 'BWP', 'Pula', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('084', 'BZD', 'Belize Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('090', 'SBD', 'Solomon Islands Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('096', 'BND', 'Brunei Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('100', 'BGL', 'Lev', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('104', 'MMK', 'Kyat', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('108', 'BIF', 'Burundi Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('116', 'KHR', 'Riel', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('124', 'CAD', 'Canadian Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('132', 'CVE', 'Cape Verde Escudo', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('136', 'KYD', 'Cayman Islands Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('144', 'LKR', 'Sri Lanka Rupee', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('152', 'CLP', 'Chilean Peso', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('156', 'CNY', 'Yuan Renminbi', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('170', 'COP', 'Colombian Peso', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('174', 'KMF', 'Comoro Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('188', 'CRC', 'Costa Rican Colon', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('191', 'HRK', 'Croatian Kuna', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('192', 'CUP', 'Cuban Peso', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('196', 'CYP', 'Cyprus Pound', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('203', 'CZK', 'Czech Koruna', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('208', 'DKK', 'Danish Krone', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('214', 'DOP', 'Dominican Peso', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('222', 'SVC', 'El Salvador Colon', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('230', 'ETB', 'Ethiopian Birr', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('232', 'ERN', 'Nakfa', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('233', 'EEK', 'Estonian Kroon', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('238', 'FKP', 'Falkland Islands Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('242', 'FJD', 'Fiji Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('262', 'DJF', 'Djibouti Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('270', 'GMD', 'Dalasi', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('288', 'GHC', 'Cedi', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('292', 'GIP', 'Gibraltar Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('320', 'GTQ', 'Quetzal', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('324', 'GNF', 'Guinea Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('328', 'GYD', 'Guyana Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('332', 'HTG', 'Gourde', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('340', 'HNL', 'Lempira', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('344', 'HKD', 'Hong Kong Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('348', 'HUF', 'Forint', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('352', 'ISK', 'Iceland Krona', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('356', 'INR', 'Indian Rupee', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('360', 'IDR', 'Rupiah', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('364', 'IRR', 'Iranian Rial', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('368', 'IQD', 'Iraqi Dinar', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('376', 'ILS', 'New Israeli Sheqel', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('388', 'JMD', 'Jamaican Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('392', 'JPY', 'Yen', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('398', 'KZT', 'Tenge', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('400', 'JOD', 'Jordanian Dinar', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('404', 'KES', 'Kenyan Shilling', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('408', 'KPW', 'North Korean Won', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('410', 'KRW', 'Won', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('414', 'KWD', 'Kuwaiti Dinar', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('417', 'KGS', 'Som', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('418', 'LAK', 'Kip', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('422', 'LBP', 'Lebanese Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('426', 'LSL', 'Lesotho Loti', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('428', 'LVL', 'Latvian Lats', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('430', 'LRD', 'Liberian Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('434', 'LYD', 'Lybian Dinar', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('440', 'LTL', 'Lithuanian Litas', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('446', 'MOP', 'Pataca', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('450', 'MGF', 'Malagasy Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('454', 'MWK', 'Kwacha', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('458', 'MYR', 'Malaysian Ringgit', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('462', 'MVR', 'Rufiyaa', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('470', 'MTL', 'Maltese Lira', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('478', 'MRO', 'Ouguiya', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('480', 'MUR', 'Mauritius Rupee', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('484', 'MXN', 'Mexican Peso', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('496', 'MNT', 'Tugrik', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('498', 'MDL', 'Moldovan Leu', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('504', 'MAD', 'Moroccan Dirham', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('508', 'MZM', 'Metical', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('512', 'OMR', 'Rial Omani', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('516', 'NAD', 'Namibia Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('524', 'NPR', 'Nepalese Rupee', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('532', 'ANG', 'Netherlands Antillan Guilder', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('533', 'AWG', 'Aruban Guilder', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('548', 'VUV', 'Vatu', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('554', 'NZD', 'New Zealand Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('558', 'NIO', 'Cordoba Oro', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('566', 'NGN', 'Naira', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('578', 'NOK', 'Norvegian Krone', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('586', 'PKR', 'Pakistan Rupee', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('590', 'PAB', 'Balboa', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('598', 'PGK', 'Kina', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('600', 'PYG', 'Guarani', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('604', 'PEN', 'Nuevo Sol', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('608', 'PHP', 'Philippine Peso', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('624', 'GWP', 'Guinea-Bissau Peso', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('634', 'QAR', 'Qatari Rial', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('642', 'ROL', 'Leu', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('643', 'RUB', 'Russian Ruble', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('646', 'RWF', 'Rwanda Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('654', 'SHP', 'Saint Helena Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('678', 'STD', 'Dobra', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('682', 'SAR', 'Saudi Riyal', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('690', 'SCR', 'Seychelles Rupee', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('694', 'SLL', 'Leone', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('702', 'SGD', 'Singapore Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('703', 'SKK', 'Slovak Koruna', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('704', 'VND', 'Dong', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('706', 'SOS', 'Somali Shilling', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('710', 'ZAR', 'Rand', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('716', 'ZWD', 'Zimbabwe Dollar', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('728', 'SSP', 'South Sudanese pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('740', 'SRG', 'Suriname Guilder', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('748', 'SZL', 'Lilangeni', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('752', 'SEK', 'Swedish Krona', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('756', 'CHF', 'Swiss Franc', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('760', 'SYP', 'Syrian Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('764', 'THB', 'Baht', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('776', 'TOP', 'Paanga', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('780', 'TTD', 'Trinidad and Tobago Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('784', 'AED', 'UAE Dirham', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('788', 'TND', 'Tunisian Dinar', 3);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('795', 'TMM', 'Manat', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('800', 'UGX', 'Uganda Shilling', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('807', 'MKD', 'Denar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('810', 'RUR', 'Russian Ruble', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('818', 'EGP', 'Egyptian Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('826', 'GBP', 'Pound Sterling', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('834', 'TZS', 'Tanzanian Shilling', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('840', 'USD', 'US Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('858', 'UYU', 'Peso Uruguayo', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('860', 'UZS', 'Uzbekistan Sum', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('862', 'VEB', 'Bolivar', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('882', 'WST', 'Tala', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('886', 'YER', 'Yemeni Rial', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('891', 'CSD', 'Serbian Dinar', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('894', 'ZMK', 'Kwacha', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('901', 'TWD', 'New Taiwan Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('933', 'BYN', 'Belarussian New Ruble', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('934', 'TMT', 'Turkmenistani Manat', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('937', 'VEF', 'Venezuelan Bolivar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('938', 'SDG', 'Sudanese Pound', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('941', 'RSD', 'Serbian Dinar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('943', 'MZN', 'Mozambican Metical', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('944', 'AZN', 'Azerbaijani Manat', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('946', 'RON', 'New Romanian Leu', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('949', 'TRY', 'New Turkish Lira', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('950', 'XAF', 'CFA Franc BEAC', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('951', 'XCD', 'East Carribbean Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('952', 'XOF', 'CFA Franc BCEAO', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('953', 'XPF', 'CFP Franc', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('967', 'ZMW', 'Zambian Kwacha', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('968', 'SRD', 'Surinamese Dollar', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('969', 'MGA', 'Malagasy Ariary', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('971', 'AFN', 'Afghani', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('972', 'TJS', 'Somoni', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('973', 'AOA', 'Kwanza', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('974', 'BYR', 'Belarussian Ruble', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('975', 'BGN', 'Bulgarian Lev', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('976', 'CDF', 'Franc Congolais', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('977', 'BAM', 'Convertible Marks', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('978', 'EUR', 'Euro', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('979', 'MXV', 'Mexican Unidad de Inversion (UDI)', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('980', 'UAH', 'Hryvnia', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('981', 'GEL', 'Lari', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('984', 'BOV', 'Mvdol', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('985', 'PLN', 'Zloty', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('986', 'BRL', 'Brazilian Real', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('990', 'CLF', 'Unidades de Fomento', 0);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('997', 'USN', 'US dollar (next day funds code)', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('998', 'USS', 'US dollar (same day funds code)', 2);
INSERT INTO currency_codes (numeric_code, alpha_code, description, exponent) VALUES ('999', 'XXX', 'No currency', 0);
INSERT INTO port_layouts (device_oid,numbering_scheme,row_count) VALUES ('.1.3.6.1','4','2');
COMMIT;
