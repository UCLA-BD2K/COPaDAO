COPaKB RESTfulAPI
===================
	BD2K Center of Excellence for Big Data Computing at UCLA

	Alan Kha            akhahaha@gmail.com
	Hannah Chou         hannahjchou@gmail.com
	Vincent Kyi         vincekyi@gmail.com
	Kevin Sheu          kevinsheu@ucla.edu
-------------------------------------------------------------------------------
Overview
---------------
COPaKB database access functions for the RESTful API.

Calls
---------------
**Disease**
- addDisease
- searchDisease
- getInitializedDisease
- addDiseaseGene
- searchDiseaseGene
- limitedGeneList

**Peptide**
- addPeptide
- list
- limitedList
- getLocation
- searchById
- getInitializedPeptide
- searchBySequence
- searchPeptidesByProtein
- searchBySpecId
- searchByPartialSequence
- addSpectrum
- getSpectrum
- getInitializedSpectrum
- updateSpectrumSpecies
- updateSpectrumFeature
- searchSpectrum
- searchSpectrumBySequence
- searchSpectrumBySequenceAndCharge
- searchSpectrumByAll
- addSpecies
- searchSpecies
- searchSpecies
- addLibraryModule
- searchLibraryModuleWithId
- searchLibraryModuleWithModule
- getLibraryModules
- addPtmType
- searchPtmType

**ProteinDAO**
- addProteinCurrent
- updateProteinCurrent
- deleteProteinCurrent
- compareProteinCurrent
- addProteinHistory
- searchProteinHistory
- addSpectrumProteinHistory
- deleteSpectrumProtein
- searchSpectrumProteinHistory
- addVersion
- searchVersion
- searchLatestVersion
- searchByID
- getInitializedProtein
- searchByLikeID
- searchByPartialID
- searchByName
- searchByLikeName
- searchBySpecies
- searchBySpeciesName
- searchByPartialSequence
- list
- limitedList
- searchDbRefByID
- searchByPDB
- searchGene
- searchByGeneSymbol
- searchByGeneID
- searchByEnsg
- getProteinWithGenes
- addGoTerms
- searchByGOAccession
- getProteinWithGoTerms
- addSpecies
- searchSpecies
- addSpectrumProtein
- getProteinWithSpectra
- searchSpectrumProtein
- searchSpectrumProteins
- searchSpectrumProteins
- searchProteinsByPeptide
- addHPAProtein
- searchHPAByID
- getInitializedHPAProtein
- addAntibody
- searchAntibodyByID
- getProteinWithPTMs
- smartSearch

**ServiceDAO**
- getReferenceProteinBundle
- getReferencePeptideBundle
- getLibModNames

**SpectrumDAO**
- searchBySpecID
- searchByID
- retrieveSpectraList
- addSpectraInfo

**StatisticsDAO**
- getModuleStatistics
- list
