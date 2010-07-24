$LOAD_PATH << ENV['JRUBY_HOME'] + '/lib/ruby/1.8'
$LOAD_PATH << ENV['JRUBY_HOME'] + '/lib/ruby/site_ruby/shared'
$LOAD_PATH << ENV['JRUBY_HOME'] + '/lib/ruby/gems/1.8/gems/bio-1.4.0/lib'

require 'bio'
require 'bio/shell'

include Java
include Bio::Shell

include_class 'cytoscape.Cytoscape'

Bio::Shell::Setup.new
Bio::Shell.load_session

if Bio::Shell.cache[:rails]
  Bio::Shell.cache[:rails].join
else
  Signal.trap("SIGINT") do
    Bio::Shell.cache[:irb].signal_handle
  end

  catch(:IRB_EXIT) do
    Bio::Shell.cache[:irb].eval_input
  end
end

Bio::Shell.save_session
