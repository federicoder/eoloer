import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { TagPersonaComponent } from 'app/entities/tag-persona/tag-persona.component';
import { TagPersonaService } from 'app/entities/tag-persona/tag-persona.service';
import { TagPersona } from 'app/shared/model/tag-persona.model';

describe('Component Tests', () => {
  describe('TagPersona Management Component', () => {
    let comp: TagPersonaComponent;
    let fixture: ComponentFixture<TagPersonaComponent>;
    let service: TagPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TagPersonaComponent],
      })
        .overrideTemplate(TagPersonaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TagPersonaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TagPersonaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TagPersona(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tagPersonas && comp.tagPersonas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
