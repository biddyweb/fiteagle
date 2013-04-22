package org.fiteagle.interactors.sfa.describe;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniCompressedOption;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.SFAOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsStruct;

public class DescribeOptions extends SFAOptionsStruct {

	private GeniCompressedOption geni_compressed;
	private Geni_RSpec_Version geni_rspec_version;
	
	
	@Override
	public List<? extends SFAOption> getOptions() {
		List<SFAOption> returnList = new ArrayList<>();
		returnList.add(geni_compressed);
		returnList.add(geni_rspec_version);
		return returnList;
	}




	public GeniCompressedOption getGeni_compressed() {
		return geni_compressed;
	}




	public void setGeni_compressed(GeniCompressedOption geni_compressed) {
		this.geni_compressed = geni_compressed;
	}




	public Geni_RSpec_Version getGeni_rspec_version() {
		return geni_rspec_version;
	}




	public void setGeni_rspec_version(Geni_RSpec_Version geni_rspec_version) {
		this.geni_rspec_version = geni_rspec_version;
	}

}
