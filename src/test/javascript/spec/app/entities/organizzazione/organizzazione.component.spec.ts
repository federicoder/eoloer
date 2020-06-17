import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { OrganizzazioneComponent } from 'app/entities/organizzazione/organizzazione.component';
import { OrganizzazioneService } from 'app/entities/organizzazione/organizzazione.service';
import { Organizzazione } from 'app/shared/model/organizzazione.model';

describe('Component Tests', () => {
  describe('Organizzazione Management Component', () => {
    let comp: OrganizzazioneComponent;
    let fixture: ComponentFixture<OrganizzazioneComponent>;
    let service: OrganizzazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [OrganizzazioneComponent],
      })
        .overrideTemplate(OrganizzazioneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganizzazioneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizzazioneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Organizzazione(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.organizzaziones && comp.organizzaziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
